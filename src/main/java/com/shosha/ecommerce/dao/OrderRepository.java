package com.shosha.ecommerce.dao;

import com.shosha.ecommerce.entity.Order;
import com.shosha.ecommerce.entity.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByOrderTrackingNumber(String orderTrackingNumber);
    List<Order> findAllByCustomerId(Long customerId);

    @Transactional
    @Modifying
    @Query("""
        UPDATE Order o
        SET o.status = :status
        WHERE o.id = :orderId
       """)
    int updateStatus(@Param("orderId") Long orderId, @Param("status") String status);

    List<Order> getAllByStatus(String status);

    @Transactional
    @Modifying
    @Query("""
        UPDATE Order o
        SET o.status = :status
        WHERE o.status = :prevStatus
       """)
    int updateStatusByPrevStatus(@Param("prevStatus") String prevStatus, @Param("status") String status);

}
