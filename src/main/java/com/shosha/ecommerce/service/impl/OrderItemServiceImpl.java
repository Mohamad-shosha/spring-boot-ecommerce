package com.shosha.ecommerce.service.impl;

import com.shosha.ecommerce.dao.OrderItemRepository;
import com.shosha.ecommerce.entity.OrderItem;
import com.shosha.ecommerce.service.OrderItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemServiceImpl(final OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    @Transactional
    public OrderItem save(final OrderItem item) {
        log.info("Saving new order item: {}", item);
        return orderItemRepository.save(item);
    }

    @Override
    @Transactional
    public OrderItem update(final OrderItem item) {
        log.info("Updating order item with ID: {}", item.getId());
        orderItemRepository.findById(item.getId())
                .orElseThrow(() -> new IllegalArgumentException("Order item not found with id: " + item.getId()));
        return orderItemRepository.save(item);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderItem> findAll() {
        log.info("Fetching all order items");
        return orderItemRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrderItem> findOne(final Long id) {
        log.info("Fetching order item with ID: {}", id);
        return orderItemRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(final Long id) {
        log.info("Deleting order item with ID: {}", id);
        if (!orderItemRepository.existsById(id)) {
            throw new IllegalArgumentException("Cannot delete — order item not found with id: " + id);
        }
        orderItemRepository.deleteById(id);
    }
}
