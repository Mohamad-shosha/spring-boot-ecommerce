package com.shosha.ecommerce.service.mapper;


import com.shosha.ecommerce.dto.FAQsDTO;
import com.shosha.ecommerce.entity.FAQs;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface FAQsMapper extends EntityMapper<FAQsDTO, FAQs> {
}

