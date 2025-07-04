package com.shosha.ecommerce.controller;

import com.shosha.ecommerce.dto.CanceledOrderDTO;
import com.shosha.ecommerce.dto.OrderDTO;
import com.shosha.ecommerce.dto.UserDTO;
import com.shosha.ecommerce.service.CanceledOrderService;
import com.shosha.ecommerce.service.OrderService;
import com.shosha.ecommerce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final OrderService orderService;
    private final UserService userService;
    private final CanceledOrderService canceledOrderService;

    public AdminController(OrderService orderService,
                           UserService userService,
                           CanceledOrderService canceledOrderService) {
        this.orderService = orderService;
        this.userService = userService;
        this.canceledOrderService = canceledOrderService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/customers")
    public ResponseEntity<List<UserDTO>> getCustomers() {
        return ResponseEntity.ok(userService.getAllCustomers());
    }

    @GetMapping("/canceled-orders")
    public ResponseEntity<List<CanceledOrderDTO>> getCanceledOrders() {
        return ResponseEntity.ok(canceledOrderService.findAll());
    }

    //TODO:
    /**
     * 1- crud operation on products
     * 2- crud operation on categories
     * 3- promote user
     * */
}
