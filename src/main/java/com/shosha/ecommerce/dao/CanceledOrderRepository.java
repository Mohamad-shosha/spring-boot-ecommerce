package com.shosha.ecommerce.dao;

import com.shosha.ecommerce.entity.CanceledOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CanceledOrderRepository extends JpaRepository<CanceledOrder, Long> {
    Optional<CanceledOrder> findByOrderId(Long orderId);
    List<CanceledOrder> findByUserId(Long orderId);
}
