package com.shosha.ecommerce.service;

import com.shosha.ecommerce.dto.*;

public interface AuthenticationService {

    UserDTO register(SignUpRequestDTO signUpRequestDTO);

    JwtAuthenticationResponse login(SignInRequestDTO signInRequestDTO);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);

    void logout(String token);
}
