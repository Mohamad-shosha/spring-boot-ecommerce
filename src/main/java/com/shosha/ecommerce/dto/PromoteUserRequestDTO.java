package com.shosha.ecommerce.dto;

import java.io.Serializable;

public class PromoteUserRequestDTO implements Serializable {
    private String userEmail;
    private String role;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
