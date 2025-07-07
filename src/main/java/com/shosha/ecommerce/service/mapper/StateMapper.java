package com.shosha.ecommerce.service.mapper;

import com.shosha.ecommerce.dto.StateDTO;
import com.shosha.ecommerce.entity.State;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StateMapper extends EntityMapper<StateDTO, State> {

    @Mapping(source = "country.id", target = "countryId")
    StateDTO toDto(State state);

    @InheritInverseConfiguration
    @Mapping(target = "country", ignore = true)
    State toEntity(StateDTO dto);
}
