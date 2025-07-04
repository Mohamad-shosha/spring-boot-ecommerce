package com.shosha.ecommerce.dto;

import com.shosha.ecommerce.entity.Address;
import com.shosha.ecommerce.entity.Order;
import com.shosha.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

public class Purchase {
    private CustomerDTO customer;
    private Order order;
    private Address shippingAddress;
    private Address billingAddress;
    private Set<OrderItem> orderItems;

    public Purchase() {
    }

    public Purchase(CustomerDTO customer,
                    Order order,
                    Address shippingAddress,
                    Address billingAddress,
                    Set<OrderItem> orderItems) {
        this.customer = customer;
        this.order = order;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
        this.orderItems = orderItems;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
