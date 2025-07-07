package com.shosha.ecommerce.dto;

import java.io.Serializable;

public class NlpQuestionDTO implements Serializable {
    private String text;
    private int limit;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
