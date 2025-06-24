package com.shosha.ecommerce.service.impl;

import com.shosha.ecommerce.dto.Purchase;
import com.shosha.ecommerce.dto.PurchaseResponse;
import com.shosha.ecommerce.entity.Address;
import com.shosha.ecommerce.entity.Customer;
import com.shosha.ecommerce.entity.Order;
import com.shosha.ecommerce.entity.OrderItem;
import com.shosha.ecommerce.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;


@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CustomerService customerService;

    @Autowired
    public CheckoutServiceImpl(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // retrieve the order info from dto
        Order order = purchase.getOrder();

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // populate order with orderItems
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(order::addOrderItem);

        // populate order with billingAddress and shippingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        // populate customer with order
        Customer customer = purchase.getCustomer();
        customer.addOrder(order);

        // save to the database
        customerService.save(customer);

        // return a response
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        // generate a random UUID number
        return UUID.randomUUID().toString();

    }
}
