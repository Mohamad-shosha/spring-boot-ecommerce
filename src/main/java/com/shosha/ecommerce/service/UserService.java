package com.shosha.ecommerce.service;

import com.shosha.ecommerce.dto.CustomerDTO;
import com.shosha.ecommerce.dto.UserDTO;
import com.shosha.ecommerce.entity.enums.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDTO save(UserDTO userDTO);

    Optional<UserDTO> findOne(String email);

    boolean existsByEmail(String email);

    UserDetailsService userDetailsService();

    Optional<UserDTO> getByRole(Role role);

    Optional<UserDTO> getByEmail(String email);

    List<UserDTO> getAllCustomers();
}
