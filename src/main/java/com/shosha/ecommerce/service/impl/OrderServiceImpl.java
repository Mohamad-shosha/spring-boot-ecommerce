package com.shosha.ecommerce.service.impl;

import com.shosha.ecommerce.dao.OrderRepository;
import com.shosha.ecommerce.entity.Order;
import com.shosha.ecommerce.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public Order save(final Order order) {
        log.info("Saving new order: {}", order);
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order update(final Order order) {
        log.info("Updating order with ID: {}", order.getId());
        orderRepository.findById(order.getId())
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + order.getId()));
        return orderRepository.save(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll() {
        log.info("Fetching all orders");
        return orderRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Order> findOne(final Long id) {
        log.info("Fetching order with ID: {}", id);
        return orderRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(final Long id) {
        log.info("Deleting order with ID: {}", id);
        if (!orderRepository.existsById(id)) {
            throw new IllegalArgumentException("Cannot delete — order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }
}
