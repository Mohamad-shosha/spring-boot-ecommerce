package com.shosha.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "order")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="order_tracking_number")
    private String orderTrackingNumber;

    @Column(name="total_quantity")
    private int totalQuantity;

    @Column(name="total_price")
    private BigDecimal totalPrice;

    @Column(name="status")
    private String status;

    @Column(name="date_created")
    @CreationTimestamp
    private Date createdAt;

    @Column(name="last_updated")
    @UpdateTimestamp
    private Date updatedAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_address_id", referencedColumnName = "id")
    private Address shippingAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
    private Address billingAddress;

    public void addOrderItem(OrderItem orderItem) {
        if (orderItems == null) {
            orderItems = new HashSet<OrderItem>();
        }
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
}

