package com.shosha.ecommerce.service.mapper;

import com.shosha.ecommerce.dto.CountryDTO;
import com.shosha.ecommerce.entity.Country;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = StateMapper.class)
public interface CountryMapper {

    CountryDTO toDto(Country country);

    @InheritInverseConfiguration
    @Mapping(target = "states", ignore = true)
    Country toEntity(CountryDTO dto);

    List<CountryDTO> toDto(List<Country> countries);
    List<Country>   toEntity(List<CountryDTO> dtos);
}
