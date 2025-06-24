package com.shosha.ecommerce.dto;

import com.shosha.ecommerce.entity.Address;
import com.shosha.ecommerce.entity.Customer;
import com.shosha.ecommerce.entity.Order;
import com.shosha.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Order order;
    private Address shippingAddress;
    private Address billingAddress;
    private Set<OrderItem> orderItems;
}
