package com.shosha.ecommerce.service;

import com.shosha.ecommerce.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;

public interface JWTService {
    String generateToken(UserDetails userDetails, UserDTO account);

    String generateToken(UserDTO userDTO);

    String extractUserName(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    String generateRefreshToken(HashMap<String, Object> map, UserDTO user);

    void disableToken(String token);
}
