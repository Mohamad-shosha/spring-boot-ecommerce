package com.shosha.ecommerce.service;

import com.shosha.ecommerce.dto.*;
import com.shosha.ecommerce.service.chatbot.NlpServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class ChatbotService {

    private final NlpServiceClient nlpClient;

    @Value("${nlp.version}")
    private Long nlpVersion;

    @Value("${nlp.username}")
    private String username;

    @Value("${nlp.password}")
    private String password;

    public ChatbotService(NlpServiceClient nlpClient) {
        this.nlpClient = nlpClient;
    }

    public NlpAnswerResponseDTO ask(NlpQuestionDTO nlpQuestionDTO) {
        return nlpClient.getAnswer(nlpVersion, nlpQuestionDTO);
    }

}
