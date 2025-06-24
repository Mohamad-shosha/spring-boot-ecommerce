package com.shosha.ecommerce.service.impl;

import com.shosha.ecommerce.dao.OrderRepository;
import com.shosha.ecommerce.entity.Order;
import com.shosha.ecommerce.service.OrderService;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Order> findOne(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}

