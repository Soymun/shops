package com.example.shop.Mappers;


import com.example.shop.DTO.TypeOfFood.TypeOfFoodCreateDto;
import com.example.shop.DTO.TypeOfFood.TypeOfFoodDto;
import com.example.shop.DTO.TypeOfFood.TypeOfFoodUpdateDto;
import com.example.shop.Entity.TypeOfFood;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeOfFoodMapper {

    TypeOfFood typeOfFoodCreateDtoToTypeOfFood(TypeOfFoodCreateDto typeOfFoodCreateDto);

    TypeOfFoodDto typeOfFoodToTypeOfFoodCreateDto(TypeOfFood typeOfFood);

    TypeOfFoodUpdateDto typeOfFoodToTypeOfFoodUpdateDto(TypeOfFoodDto typeOfFoodDto);
}
