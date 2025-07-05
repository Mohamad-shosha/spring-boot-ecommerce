package com.shosha.ecommerce.dto;

import com.shosha.ecommerce.service.annotations.UniqueEmail;

import javax.validation.constraints.*;
import java.io.Serializable;

public class SignUpRequestDTO implements Serializable {

    @NotBlank(message = "is required")
    private String firstName;

    private String lastName;

    @NotBlank(message = "is required")
    @Email(message = "Invalid email format")
    @UniqueEmail
    private String email;

    @NotBlank(message = "is required")
    @Size(min = 8, message = "must be at least 8 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "must contain at least 8 characters, including uppercase, lowercase, number, and special character"
    )
    private String password;

    public SignUpRequestDTO() {
    }

    public SignUpRequestDTO(String firstName,
                            String lastName,
                            String email,
                            String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
