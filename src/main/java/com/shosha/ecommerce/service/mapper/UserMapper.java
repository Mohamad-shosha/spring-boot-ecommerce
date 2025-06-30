package com.shosha.ecommerce.service.mapper;


import com.shosha.ecommerce.dto.UserDTO;
import com.shosha.ecommerce.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User> {

}
