package com.shosha.ecommerce.controller;

import com.shosha.ecommerce.dto.CanceledOrderDTO;
import com.shosha.ecommerce.dto.OrderDTO;
import com.shosha.ecommerce.entity.enums.OrderStatus;
import com.shosha.ecommerce.service.CanceledOrderService;
import com.shosha.ecommerce.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final CanceledOrderService canceledOrderService;
    private final OrderService orderService;

    public OrderController(CanceledOrderService canceledOrderService,
                           OrderService orderService) {
        this.canceledOrderService = canceledOrderService;
        this.orderService = orderService;
    }

    @GetMapping("/")
    public ResponseEntity<List<OrderDTO>> getOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/canceled")
    public ResponseEntity<List<CanceledOrderDTO>> getCanceledOrders() {
        return ResponseEntity.ok(canceledOrderService.findAll());
    }

    @PutMapping("/confirm/{otn}")
    public ResponseEntity<OrderDTO> confirmOrder(@PathVariable("otn") String otn) {
        OrderDTO orderDTO = orderService.getOrderByOrderTrackingNumber(otn).orElseThrow();
        orderService.updateOrderStatus(orderDTO.getId(), OrderStatus.CONFIRMED.name());
        orderDTO.setStatus(OrderStatus.CONFIRMED.name());
        return ResponseEntity.ok(orderDTO);
    }
}
