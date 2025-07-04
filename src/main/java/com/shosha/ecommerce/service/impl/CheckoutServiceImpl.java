package com.shosha.ecommerce.service.impl;

import com.shosha.ecommerce.dao.OrderRepository;
import com.shosha.ecommerce.dto.*;
import com.shosha.ecommerce.entity.Order;
import com.shosha.ecommerce.entity.OrderItem;
import com.shosha.ecommerce.entity.User;
import com.shosha.ecommerce.entity.enums.OrderStatus;
import com.shosha.ecommerce.service.CheckoutService;
import com.shosha.ecommerce.service.OrderItemService;
import com.shosha.ecommerce.service.OrderService;
import com.shosha.ecommerce.service.UserService;
import com.shosha.ecommerce.service.mapper.OrderItemMapper;
import com.shosha.ecommerce.service.mapper.OrderMapper;
import com.shosha.ecommerce.service.mapper.UserMapper;
import com.shosha.ecommerce.service.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final UserMapper userMapper;
    private final OrderRepository orderRepository;

    public CheckoutServiceImpl(UserMapper userMapper,
                               OrderRepository orderRepository) {
        this.userMapper = userMapper;
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        Order order = purchase.getOrder();
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());
        order.setStatus(OrderStatus.PENDING.name());

        UserDTO user = SecurityUtil.getCurrentUser();
        order.setCustomer(userMapper.toEntity(user));

        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(i -> {
            OrderItem item = new OrderItem();
            item.setQuantity(i.getQuantity());
            item.setUnitPrice(i.getUnitPrice());
            item.setProductId(i.getProductId());
            item.setImageUrl(i.getImageUrl());
            order.addItem(item);
        } );
        orderRepository.save(order);
        log.info("Order placed successfully with tracking number: {}", orderTrackingNumber);
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
