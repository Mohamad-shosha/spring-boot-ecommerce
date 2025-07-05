package com.shosha.ecommerce.controller;

import com.shosha.ecommerce.dto.*;
import com.shosha.ecommerce.entity.enums.Role;
import com.shosha.ecommerce.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/customers")
    public ResponseEntity<List<UserDTO>> getCustomers() {
        return ResponseEntity.ok(userService.getAllCustomers());
    }

    @GetMapping("/roles")
    public ResponseEntity<Role[]> getSystemRoles() {
        return ResponseEntity.ok(Role.values());
    }

    @PutMapping("/users/promote")
    public ResponseEntity<UserDTO> promoteUser(@RequestBody PromoteUserRequestDTO promoteUserRequestDTO) {
        UserDTO userDTO = userService.getByEmail(promoteUserRequestDTO.getUserEmail()).orElseThrow();
        Role role = Role.valueOf(promoteUserRequestDTO.getRole());
        userDTO.setRole(role.name());
        userService.save(userDTO);
        return ResponseEntity.ok(userDTO);
    }
}
