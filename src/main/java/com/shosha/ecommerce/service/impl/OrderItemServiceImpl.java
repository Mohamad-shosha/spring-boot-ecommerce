package com.shosha.ecommerce.service.impl;

import com.shosha.ecommerce.dao.OrderItemRepository;
import com.shosha.ecommerce.entity.OrderItem;
import com.shosha.ecommerce.service.OrderItemService;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderItem save(OrderItem item) {
        return orderItemRepository.save(item);
    }

    @Override
    public OrderItem update(OrderItem item) {
        return orderItemRepository.save(item);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrderItem> findOne(Long id) {
        return orderItemRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        orderItemRepository.deleteById(id);
    }
}

