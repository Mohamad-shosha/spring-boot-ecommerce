package com.shosha.ecommerce.service.util;

import com.shosha.ecommerce.dto.UserDTO;
import com.shosha.ecommerce.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

    private static UserService userService;

    private SecurityUtil(UserService userService) {
        SecurityUtil.userService = userService;
    }

    public static UserDTO getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().toString().equals("anonymousUser")) {
            return null;
        }
        String email = authentication.getName();
        return userService.findOne(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}
