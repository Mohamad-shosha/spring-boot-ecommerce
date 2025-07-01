package com.shosha.ecommerce.dto;

import com.shosha.ecommerce.entity.Order;
import com.shosha.ecommerce.entity.User;
import lombok.Getter;
import lombok.Setter;


import java.util.HashSet;
import java.util.Set;
public class CustomerDTO extends UserDTO{

    private Set<Order> orders;

    public CustomerDTO() {
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        if (orders == null) {
            orders = new HashSet<>();
        }
        orders.add(order);

        order.setCustomer(new User(this));
    }

}
