package com.shosha.ecommerce.service.mapper;

import com.shosha.ecommerce.dto.OrderDTO;
import com.shosha.ecommerce.entity.Order;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = { UserMapper.class, AddressMapper.class, OrderItemMapper.class }
)
public interface OrderMapper {

    @Mapping(target = "customer",         source = "customer")
    @Mapping(target = "shippingAddress",  source = "shippingAddress")
    @Mapping(target = "billingAddress",   source = "billingAddress")
    @Mapping(target = "orderItems",       source = "orderItems")
    OrderDTO toDto(Order order);

    @InheritInverseConfiguration
    Order toEntity(OrderDTO dto);

    List<OrderDTO> toDto(List<Order> orders);
    List<Order>   toEntity(List<OrderDTO> dtos);
}
