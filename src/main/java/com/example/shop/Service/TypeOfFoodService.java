package com.example.shop.Service;


import com.example.shop.DTO.TypeOfFood.TypeOfFoodCreateDto;
import com.example.shop.DTO.TypeOfFood.TypeOfFoodDto;
import com.example.shop.DTO.TypeOfFood.TypeOfFoodUpdateDto;

import java.util.List;

public interface TypeOfFoodService {

    Long createTypeOfFood(TypeOfFoodCreateDto typeOfFoodCreateDto);

    TypeOfFoodDto getTypeOfFoodById(Long id);

    TypeOfFoodDto updateTypeOfFood(TypeOfFoodUpdateDto typeOfFoodUpdateDto);

    void deleteTypeOfFoodById(Long id);

    List<TypeOfFoodDto> getSubTypeOfFoodByRootIdTypeOfFood(Long rootId);

    List<TypeOfFoodDto> getRootTypeOfFood();
}
