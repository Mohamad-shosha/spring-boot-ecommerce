package com.shosha.ecommerce.dao;

import com.shosha.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByOrderTrackingNumber(String orderTrackingNumber);
    List<Order> findAllByCustomerId(Long customerId);
}
