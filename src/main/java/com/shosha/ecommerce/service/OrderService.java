package com.shosha.ecommerce.service;

import com.shosha.ecommerce.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order save(Order order);
    Order update(Order order);
    List<Order> findAll();
    Optional<Order> findOne(Long id);
    void delete(Long id);
}
