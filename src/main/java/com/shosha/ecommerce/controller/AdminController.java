package com.shosha.ecommerce.controller;

import com.shosha.ecommerce.dto.OrderDTO;
import com.shosha.ecommerce.dto.UserDTO;
import com.shosha.ecommerce.service.OrderService;
import com.shosha.ecommerce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final OrderService orderService;
    private final UserService userService;

    public AdminController(OrderService orderService,
                           UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>>orders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/customers")
    public ResponseEntity<List<UserDTO>>users() {
        return ResponseEntity.ok(userService.getAllCustomers());
    }

    @PutMapping("/upgrade-user")
    public void upgradeUser() {

    }
}
