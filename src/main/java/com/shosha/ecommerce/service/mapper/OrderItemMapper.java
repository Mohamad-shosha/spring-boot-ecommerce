package com.shosha.ecommerce.service.mapper;

import com.shosha.ecommerce.dto.OrderItemDTO;
import com.shosha.ecommerce.entity.OrderItem;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(target = "id",         source = "id")
    @Mapping(target = "imageUrl",   source = "imageUrl")
    @Mapping(target = "unitPrice",  source = "unitPrice")
    @Mapping(target = "quantity",   source = "quantity")
    @Mapping(target = "productId",  source = "productId")
    OrderItemDTO toDto(OrderItem entity);

    @InheritInverseConfiguration
    @Mapping(target = "order", ignore = true)
    OrderItem toEntity(OrderItemDTO dto);

    List<OrderItemDTO> toDto(List<OrderItem> entities);
    List<OrderItem>    toEntity(List<OrderItemDTO> dtos);
}
