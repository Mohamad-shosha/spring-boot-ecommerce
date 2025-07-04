package com.shosha.ecommerce.service.mapper;

import com.shosha.ecommerce.dto.CanceledOrderDTO;
import com.shosha.ecommerce.entity.CanceledOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CanceledOrderMapper extends EntityMapper<CanceledOrderDTO, CanceledOrder>{
}
