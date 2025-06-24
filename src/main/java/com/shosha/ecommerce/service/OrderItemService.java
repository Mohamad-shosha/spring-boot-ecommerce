package com.shosha.ecommerce.service;

import com.shosha.ecommerce.entity.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {
    OrderItem save(OrderItem item);
    OrderItem update(OrderItem item);
    List<OrderItem> findAll();
    Optional<OrderItem> findOne(Long id);
    void delete(Long id);
}
