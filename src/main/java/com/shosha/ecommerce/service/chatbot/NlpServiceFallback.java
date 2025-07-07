package com.shosha.ecommerce.service.chatbot;

import com.shosha.ecommerce.dto.*;
import org.springframework.web.multipart.MultipartFile;

public class NlpServiceFallback implements NlpServiceClient {

    @Override
    public NlpAnswerResponseDTO getAnswer(Long version, NlpQuestionDTO body) {
        NlpAnswerResponseDTO fallback = new NlpAnswerResponseDTO();
        fallback.setId(version);
        fallback.setAnswer("Service unavailable, please try again later");
        return fallback;
    }

    @Override
    public NlpUploadFileResponseDTO updateKnowledgeBase(Long version, MultipartFile incoming) {
        NlpUploadFileResponseDTO fallback = new NlpUploadFileResponseDTO();
        fallback.setSignal("Service unavailable, please try again later");
        return fallback;
    }

    @Override
    public NlpProcessResponseDTO nlpProcess(String basicAuthToken, Long version, NlpProcessRequestDTO nlpProcessRequestDTO) {
        return null;
    }

    @Override
    public NlpPushResponseDTO nlpPush(Long version, NlpPushRequestDTO nlpPushRequestDTO) {
        return null;
    }

}

