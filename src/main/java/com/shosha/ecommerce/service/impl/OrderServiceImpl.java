package com.shosha.ecommerce.service.impl;


import java.util.List;
import java.util.Optional;

import com.shosha.ecommerce.dao.OrderRepository;
import com.shosha.ecommerce.dto.CancelOrderRequestDTO;
import com.shosha.ecommerce.dto.OrderDTO;
import com.shosha.ecommerce.dto.UpdateOrderInfoRequestDTO;
import com.shosha.ecommerce.entity.Order;
import com.shosha.ecommerce.entity.enums.OrderStatus;
import com.shosha.ecommerce.service.OrderService;
import com.shosha.ecommerce.service.mapper.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        log.debug("Request to save order : {}", orderDTO);
        Order order = orderMapper.toEntity(orderDTO);
        order = orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    @Override
    public OrderDTO update(UpdateOrderInfoRequestDTO orderInfoDTO) {
        log.debug("Request to update order : {}", orderInfoDTO);
        Long orderId = orderInfoDTO.getOrderId();
        Optional<OrderDTO> dbOrder = findOne(orderId);
        if(orderInfoDTO.getOrderId()==null || dbOrder.isEmpty()) {
            throw new IllegalArgumentException("Cannot update; order not found (id=" + orderId + ")");
        }

        OrderDTO orderDTO = dbOrder.get();
        if(orderInfoDTO.getShippingAddress()!=null) {
            orderDTO.setShippingAddress(orderInfoDTO.getShippingAddress());
        }
        if (orderInfoDTO.getPillingAddress()!=null) {
            orderDTO.setBillingAddress(orderInfoDTO.getPillingAddress());
        }

        return save(orderDTO);
    }


    @Override
    @Transactional(readOnly = true)
    public List<OrderDTO> findAll() {
        log.debug("Request to get all orders");
        return orderRepository.findAll().stream().map(orderMapper::toDto).toList();
    }

    @Override
    public List<OrderDTO> findAllByCustomerId(Long customerId) {
        return orderRepository.findAllByCustomerId(customerId).stream().map(orderMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrderDTO> findOne(Long id) {
        log.debug("Request to get order by id : {}", id);
        return orderRepository.findById(id).map(orderMapper::toDto);
    }

    @Override
    public Optional<OrderDTO> getOrderByOrderTrackingNumber(String orderTrackingNumber) {
        log.debug("Request to get order by otn : {}", orderTrackingNumber);
        return orderRepository.findByOrderTrackingNumber(orderTrackingNumber).map(orderMapper::toDto);
    }

    @Override
    public void cancelOrder(CancelOrderRequestDTO cancelOrderRequestDTO) {
        log.debug("Request to cancel order : {}", cancelOrderRequestDTO);
        Long id = cancelOrderRequestDTO.getOrderId();
        Optional<OrderDTO> dbOrder = findOne(id);

        if(cancelOrderRequestDTO.getOrderId()==null || dbOrder.isEmpty()) {
            throw new IllegalArgumentException("Cannot cancel; order not found (id=" + id + ")");
        }

        OrderDTO orderDTO = dbOrder.get();
        orderDTO.setStatus(OrderStatus.CANCELLED.name());
    }

    @Override
    public void delete(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new IllegalArgumentException("Cannot delete; order not found (id=" + id + ")");
        }
        orderRepository.deleteById(id);
    }


}
