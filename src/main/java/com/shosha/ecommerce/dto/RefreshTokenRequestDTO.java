package com.shosha.ecommerce.dto;

import java.io.Serializable;

public class RefreshTokenRequestDTO implements Serializable {
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
