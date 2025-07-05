package com.shosha.ecommerce.service;

import com.shosha.ecommerce.dto.CanceledOrderDTO;
import com.shosha.ecommerce.dto.OrderDTO;
import com.shosha.ecommerce.dto.UpdateOrderInfoRequestDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderDTO save(OrderDTO order);

    OrderDTO update(UpdateOrderInfoRequestDTO order);

    List<OrderDTO> findAll();

    List<OrderDTO> getCustomerOrders(Long customerId);

    Optional<OrderDTO> findOne(Long id);

    Optional<OrderDTO> getOrderByOrderTrackingNumber(String orderTrackingNumber);

    List<OrderDTO> getAllConfirmedOrders();

    List<OrderDTO> getAllProcessingOrders();

    void cancelOrder(CanceledOrderDTO canceledOrderDTO);

    OrderDTO restoreOrder(Long id);

    void updateOrderStatus(Long orderId, String status);

    void delete(Long id);
}
