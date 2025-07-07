package com.shosha.ecommerce.service.chatbot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.shosha.ecommerce.dao.ProductRepository;
import com.shosha.ecommerce.dto.*;
import com.shosha.ecommerce.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.stream.Stream;

@Service
public class KnowledgeBaseSyncService {

    private final Logger log = LoggerFactory.getLogger(KnowledgeBaseSyncService.class);

    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;
    private final NlpServiceClient nlpClient;

    @Value("${nlp.username}")
    private String username;

    @Value("${nlp.password}")
    private String password;

    @Value("${nlp.process.chunk.size}")
    private Integer chunkSize;

    @Value("${nlp.process.overlap.size}")
    private Integer overlapSize;

    @Value("${nlp.process.reset}")
    private Integer doReset;


    public KnowledgeBaseSyncService(ProductRepository productRepository,
                                    ObjectMapper objectMapper,
                                    NlpServiceClient nlpClient) {
        this.productRepository = productRepository;
        this.objectMapper = objectMapper;
        this.nlpClient = nlpClient;
    }

    @Value("${nlp.version}")
    private Long nlpVersion;


    @Transactional(readOnly = true)
    @Scheduled(cron = "0 0 0 * * *")
    public void syncKnowledgeBase() throws IOException {
        log.info("Starting sync knowledge base");
        Path tempFile = Files.createTempFile("products‑", ".json");

        // 1️⃣  Stream ➜ JSON
        try (Stream<Product> stream = productRepository.streamAll();
             Writer fw = Files.newBufferedWriter(tempFile);
             SequenceWriter json = objectMapper
                     .writer()
                     .with(SerializationFeature.INDENT_OUTPUT)
                     .writeValuesAsArray(fw)) {

            stream.forEach(product -> {
                try {
                    json.write(product);
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
        }

        // 2️⃣  Temp file ➜ MultipartFile (zero‑copy; bytes read only once)
        try (InputStream is = Files.newInputStream(tempFile)) {
            MultipartFile multipart =
                    new MockMultipartFile("file", "products.json",
                            "application/json", is);

            // 3️⃣  Upload
            NlpUploadFileResponseDTO resp =
                    nlpClient.updateKnowledgeBase(nlpVersion, multipart);

            log.info("✅ KB sync success (version {}): {}", nlpVersion, resp);
        } finally {
            Files.deleteIfExists(tempFile);
        }

        NlpProcessResponseDTO processResponseDTO = nlpProcess(generateNlpProcessRequestDTO());
        log.debug("processing response: {}", processResponseDTO);

        NlpPushResponseDTO pushResponseDTO = nlpPush(generateNlpPushRequestDTO());
        log.debug("pushing response: {}", pushResponseDTO);
    }

    public NlpProcessResponseDTO nlpProcess(NlpProcessRequestDTO nlpProcessRequestDTO) {
        String authToken = getNlpAuthToken();
        return nlpClient.nlpProcess(authToken, nlpVersion, nlpProcessRequestDTO);
    }

    public NlpPushResponseDTO nlpPush(NlpPushRequestDTO nlpPushRequestDTO) {
        return nlpClient.nlpPush(nlpVersion, nlpPushRequestDTO);
    }

    private String getNlpAuthToken() {
        String token = Base64
                .getEncoder()
                .encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));

        return "Basic " + token;
    }

    private NlpProcessRequestDTO generateNlpProcessRequestDTO() {
        return new NlpProcessRequestDTO(chunkSize, overlapSize, doReset);
    }

    private NlpPushRequestDTO generateNlpPushRequestDTO() {
        return new NlpPushRequestDTO(doReset);
    }
}
