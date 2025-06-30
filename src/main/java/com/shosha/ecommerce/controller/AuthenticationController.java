package com.shosha.ecommerce.controller;

import com.shosha.ecommerce.dto.*;
import com.shosha.ecommerce.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) {
        UserDTO userDTO = authenticationService.register(signUpRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody SignInRequestDTO signInRequestDTO) {
        JwtAuthenticationResponse jwtAuthResponse = authenticationService.login(signInRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(jwtAuthResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO) {
        JwtAuthenticationResponse jwtAuthResponse = authenticationService.refreshToken(refreshTokenRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(jwtAuthResponse);
    }

    @PutMapping("/logout")
    public ResponseEntity<Object> logout(@RequestBody LogoutRequestDTO logoutRequestDTO) {
        authenticationService.logout(logoutRequestDTO.getToken());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
