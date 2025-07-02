package com.shosha.ecommerce.service;

import com.shosha.ecommerce.dto.UserDTO;
import com.shosha.ecommerce.entity.enums.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminInitializer implements CommandLineRunner {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AdminInitializer(UserService userService,
                            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${admin.password}")
    private String adminPassword;

    @Override
    public void run(String... args) throws Exception {
        Optional<UserDTO> adminAccount = userService.getByRole(Role.ADMIN);
        if(adminAccount.isEmpty()) {
            UserDTO admin = new UserDTO();
            admin.setFirstName("Fares");
            admin.setLastName("Mostafa");
            admin.setEmail("fares.mostafa@gmail.com");
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setRole(Role.ADMIN.name());
            userService.save(admin);
        }
    }
}