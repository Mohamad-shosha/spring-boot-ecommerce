package com.shosha.ecommerce.service.impl;

import com.shosha.ecommerce.dto.Purchase;
import com.shosha.ecommerce.dto.PurchaseResponse;
import com.shosha.ecommerce.entity.Customer;
import com.shosha.ecommerce.entity.Order;
import com.shosha.ecommerce.entity.OrderItem;
import com.shosha.ecommerce.service.CheckoutService;
import com.shosha.ecommerce.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CustomerService customerService;

    public CheckoutServiceImpl(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(final Purchase purchase) {
        Order order = purchase.getOrder();
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(order::addOrderItem);

        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        Customer customer = purchase.getCustomer();
        customer.addOrder(order);

        customerService.save(customer);

        log.info("Order placed successfully with tracking number: {}", orderTrackingNumber);
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
