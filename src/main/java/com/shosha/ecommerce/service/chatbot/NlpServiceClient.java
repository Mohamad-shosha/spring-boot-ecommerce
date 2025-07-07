package com.shosha.ecommerce.service.chatbot;

import com.shosha.ecommerce.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(
        name = "nlpService",
        url  = "${nlp.base-url}",
        fallback = NlpServiceFallback.class
)
public interface NlpServiceClient {

    @PostMapping("/api/v1/nlp/index/answer/{version}")
    NlpAnswerResponseDTO getAnswer(
            @PathVariable("version") Long version,
            @RequestBody NlpQuestionDTO body);

    @PostMapping(value = "/api/v1/data/upload/{version}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    NlpUploadFileResponseDTO updateKnowledgeBase(
            @PathVariable("version") Long version,
            @RequestPart("file") MultipartFile file);

    @PostMapping(value = "/api/v1/data/process/{version}",
            headers = "Authorization=Basic {auth}" )
    NlpProcessResponseDTO nlpProcess(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
            @PathVariable("version") Long version,
            @RequestBody NlpProcessRequestDTO nlpProcessRequestDTO);

    @PostMapping(value = "/api/v1/nlp/index/push/{version}")
    NlpPushResponseDTO nlpPush(
            @PathVariable("version") Long version,
            NlpPushRequestDTO nlpPushRequestDTO);
}
