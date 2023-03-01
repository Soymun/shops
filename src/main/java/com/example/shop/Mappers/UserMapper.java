package com.example.shop.Mappers;

import com.example.shop.DTO.User.UserDto;
import com.example.shop.DTO.User.UserGetDto;
import com.example.shop.Entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);

    UserGetDto userToUserGetDto(User user);
}
