package com.shosha.ecommerce.controller;

import com.shosha.ecommerce.dto.CanceledOrderDTO;
import com.shosha.ecommerce.dto.OrderDTO;
import com.shosha.ecommerce.dto.UpdateOrderInfoRequestDTO;
import com.shosha.ecommerce.dto.UserDTO;
import com.shosha.ecommerce.service.CanceledOrderService;
import com.shosha.ecommerce.service.OrderService;
import com.shosha.ecommerce.service.util.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final OrderService orderService;
    private final CanceledOrderService canceledOrderService;

    public CustomerController(OrderService orderService,
                              CanceledOrderService canceledOrderService) {
        this.orderService = orderService;
        this.canceledOrderService = canceledOrderService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        UserDTO userDTO = SecurityUtil.getCurrentUser();
        assert userDTO != null;
        List<OrderDTO> orders = orderService.getCustomerOrders(userDTO.getId());
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping("/orders/{otn}")
    public ResponseEntity<OrderDTO> getOrderByOrderTrackingNumber(@PathVariable String otn) {
        OrderDTO orderDTO = orderService.getOrderByOrderTrackingNumber(otn).orElse(null);
        return ResponseEntity.ok().body(orderDTO);
    }

    @GetMapping("/orders/canceled")
    public ResponseEntity<List<CanceledOrderDTO>> getCanceledOrders() {
        return ResponseEntity.ok(canceledOrderService.findByCustomerId());
    }

    @PutMapping("/orders")
    public ResponseEntity<OrderDTO> updateOrder(@RequestBody UpdateOrderInfoRequestDTO orderInfoDTO) {
        OrderDTO updatedOrder = orderService.update(orderInfoDTO);
        return ResponseEntity.ok().body(updatedOrder);
    }

    @PutMapping("/orders/cancel")
    public ResponseEntity<?> cancelOrder(@RequestBody CanceledOrderDTO canceledOrderDTO) {
        orderService.cancelOrder(canceledOrderDTO);
        return ResponseEntity.ok("Order canceled successfully");
    }

    @PutMapping("/orders/restore")
    public ResponseEntity<?> reorder(@RequestParam("orderId") Long orderId) {
        return ResponseEntity.ok(orderService.restoreOrder(orderId));
    }

    @GetMapping("/orders/{otn}/status")
    public ResponseEntity<String> getOrderStatus(@PathVariable String otn) {
        OrderDTO orderDTO = orderService.getOrderByOrderTrackingNumber(otn).orElse(null);
        return ResponseEntity.ok().body(orderDTO.getStatus());
    }
}
