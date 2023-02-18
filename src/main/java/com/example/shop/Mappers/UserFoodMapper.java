package com.example.shop.Mappers;

import com.example.shop.DTO.UserFood.UserFoodCreateDto;
import com.example.shop.DTO.UserFood.UserFoodDto;
import com.example.shop.Entity.UserProduct;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserFoodMapper {

    UserProduct userFoodCreateDtoToUserProduct(UserFoodCreateDto userFoodCreateDto);

    UserFoodDto userProductToUserFoodDto(UserProduct userProduct);
}
