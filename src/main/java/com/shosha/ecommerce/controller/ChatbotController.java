package com.shosha.ecommerce.controller;

import com.shosha.ecommerce.dto.*;
import com.shosha.ecommerce.service.ChatbotService;
import com.shosha.ecommerce.service.FAQsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chatbot")
public class ChatbotController {

    private final ChatbotService chatbotService;
    private final FAQsService faqsService;

    public ChatbotController(ChatbotService chatbotService,
                             FAQsService faqsService) {
        this.chatbotService = chatbotService;
        this.faqsService = faqsService;
    }

    @GetMapping("/FAQs")
    public List<FAQsDTO> getFAQs() {
        return faqsService.findAll();
    }

    @GetMapping("/FAQs/{question_id}")
    public NlpAnswerResponseDTO FAQAnswer(@PathVariable("question_id") Integer questionId) {
        FAQsDTO dto = faqsService.findOne(questionId).orElseThrow();
        return new NlpAnswerResponseDTO(dto.getAnswer());
    }

    @PostMapping("/ask")
    public NlpAnswerResponseDTO ask(@RequestBody NlpQuestionDTO question) {
        NlpQuestionDTO nlpQuestionDTO = new NlpQuestionDTO();
        nlpQuestionDTO.setText(question.getText());
        nlpQuestionDTO.setLimit(3);
        return chatbotService.ask(nlpQuestionDTO);
    }
}
