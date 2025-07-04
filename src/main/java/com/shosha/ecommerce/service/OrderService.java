package com.shosha.ecommerce.service;

import com.shosha.ecommerce.dto.CancelOrderRequestDTO;
import com.shosha.ecommerce.dto.OrderDTO;
import com.shosha.ecommerce.dto.UpdateOrderInfoRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderDTO save(OrderDTO order);
    OrderDTO update(UpdateOrderInfoRequestDTO order);
    List<OrderDTO> findAll();
    List<OrderDTO> findAllByCustomerId(Long customerId);
    Optional<OrderDTO> findOne(Long id);
    Optional<OrderDTO> getOrderByOrderTrackingNumber(String orderTrackingNumber);
    void cancelOrder(CancelOrderRequestDTO cancelOrderRequestDTO);
    void delete(Long id);
}
