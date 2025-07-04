package com.shosha.ecommerce.controller;

import com.shosha.ecommerce.dto.CancelOrderRequestDTO;
import com.shosha.ecommerce.dto.OrderDTO;
import com.shosha.ecommerce.dto.UpdateOrderInfoRequestDTO;
import com.shosha.ecommerce.dto.UserDTO;
import com.shosha.ecommerce.service.OrderService;
import com.shosha.ecommerce.service.util.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final OrderService orderService;

    public CustomerController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        UserDTO userDTO = SecurityUtil.getCurrentUser();
        assert userDTO != null;
        List<OrderDTO> orders = orderService.findAllByCustomerId(userDTO.getId());
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping("/orders/{otn}")
    public ResponseEntity<OrderDTO> getOrderByOrderTrackingNumber(@PathVariable String otn) {
        OrderDTO orderDTO = orderService.getOrderByOrderTrackingNumber(otn).orElse(null);
        return ResponseEntity.ok().body(orderDTO);
    }

    @PutMapping("/orders")
    public ResponseEntity<OrderDTO> updateOrder(@RequestBody UpdateOrderInfoRequestDTO orderInfoDTO) {
        OrderDTO updatedOrder = orderService.update(orderInfoDTO);
        return ResponseEntity.ok().body(updatedOrder);
    }

    @PutMapping("/orders/cancel")
    public ResponseEntity<?> cancelOrder(@RequestBody CancelOrderRequestDTO cancelOrderRequestDTO) {
        orderService.cancelOrder(cancelOrderRequestDTO);
        return ResponseEntity.ok().build();
    }
}
