package com.shosha.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


public class LogoutRequestDTO implements Serializable {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
