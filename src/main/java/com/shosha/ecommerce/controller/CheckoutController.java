package com.shosha.ecommerce.controller;

import com.shosha.ecommerce.dto.Purchase;
import com.shosha.ecommerce.dto.PurchaseResponse;
import com.shosha.ecommerce.service.CheckoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {
    private final CheckoutService checkoutService;
    private final String ORDERS_URI = "/api/checkout/orders";

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public ResponseEntity<PurchaseResponse> placeOrder(@RequestBody Purchase purchase) throws URISyntaxException {
        return ResponseEntity.created(new URI(ORDERS_URI)).body(checkoutService.placeOrder(purchase));
    }
}
