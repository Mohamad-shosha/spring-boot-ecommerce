package com.shosha.ecommerce.service.util;

import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class ResponseUtil {
    public static <X> ResponseEntity<X> wrapOrNotFound(Optional<X> maybeResponse) {
        return maybeResponse.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
