package com.shosha.ecommerce.service.mapper;


import com.shosha.ecommerce.dto.AddressDTO;
import com.shosha.ecommerce.dto.CountryDTO;
import com.shosha.ecommerce.entity.Address;
import com.shosha.ecommerce.entity.Country;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AddressMapper extends EntityMapper<AddressDTO, Address> {

    @Mapping(target = "id",       source = "id")
    @Mapping(target = "street",   source = "street")
    @Mapping(target = "city",     source = "city")
    @Mapping(target = "state",    source = "state")
    @Mapping(target = "country",  source = "country")
    @Mapping(target = "zipCode",  source = "zipCode")
    AddressDTO toDto(Address address);

    @InheritInverseConfiguration
    @Mapping(target = "order", ignore = true)
    Address toEntity(AddressDTO dto);

    List<AddressDTO> toDto(List<Address> addresses);
    List<Address>    toEntity(List<AddressDTO> dtos);
}
