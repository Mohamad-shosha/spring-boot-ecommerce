package com.shosha.ecommerce.service.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@Component
public class DataExportUtil {

    private final ObjectMapper objectMapper;

    public DataExportUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Writes the given orders to <code>targetPath</code> as pretty‑printed JSON.
     *
     * @param data       list of data to be written
     * @param targetPath where to write (e.g. Paths.get("orders.json"))
     * @throws IOException if the file cannot be written
     */
    public void writeDataToJsonFile(List<?> data, Path targetPath) throws IOException {

        // 1️⃣  Make sure the directory exists
        Files.createDirectories(targetPath.getParent());

        // 2️⃣  Produce pretty JSON once per mapper
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // 3️⃣  Convert to bytes (saves one extra disk read)
        byte[] jsonBytes = objectMapper.writeValueAsBytes(data);

        // 4️⃣  Persist to disk
        Files.write(targetPath, jsonBytes);

        log.info("✅ Wrote {} rows to {}", data.size(), targetPath.toAbsolutePath());
    }
}
