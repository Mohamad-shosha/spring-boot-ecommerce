package com.shosha.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shosha.ecommerce.entity.enums.Role;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @JsonIgnore
    private String password;

    private Role role;

    public UserDTO() {
    }

    public UserDTO(Long id,
                       String firstName,
                       String lastName,
                       String email,
                       String password,
                       Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserDTO(SignUpRequestDTO signUpRequestDTO) {
        setFirstName(signUpRequestDTO.getFirstName());
        setLastName(signUpRequestDTO.getLastName());
        setEmail(signUpRequestDTO.getEmail());
        setPassword(signUpRequestDTO.getPassword());
        setRole(Role.CUSTOMER);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                '}';
    }
}
