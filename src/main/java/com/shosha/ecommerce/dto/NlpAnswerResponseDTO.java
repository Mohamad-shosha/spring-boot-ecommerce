package com.shosha.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NlpAnswerResponseDTO implements Serializable {

    @JsonIgnore
    private Long id;

    @JsonIgnore
    private String signal;

    private String answer;

    @JsonIgnore
    @JsonProperty("full_prompt")
    private String fullPrompt;

    @JsonIgnore
    @JsonProperty("chat_history")
    private List<ChatMessage> chatHistory;

    public NlpAnswerResponseDTO() {
    }

    public NlpAnswerResponseDTO(String answer) {
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getFullPrompt() {
        return fullPrompt;
    }

    public void setFullPrompt(String fullPrompt) {
        this.fullPrompt = fullPrompt;
    }

    public List<ChatMessage> getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(List<ChatMessage> chatHistory) {
        this.chatHistory = chatHistory;
    }

    public static class ChatMessage {
        private String role;
        private String content;

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

}
