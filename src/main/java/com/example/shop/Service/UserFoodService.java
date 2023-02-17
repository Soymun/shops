package com.example.shop.Service;

import com.example.shop.DTO.UserFood.UserFoodCreateDto;
import com.example.shop.DTO.UserFood.UserFoodDto;
import com.example.shop.DTO.UserFood.UserFoodUpdateDto;

import java.util.List;

public interface UserFoodService {

    void createUserFood(UserFoodCreateDto userFoodCreateDto);

    void deleteUserFoodById(Long id);

    UserFoodDto getUserFoodById(Long id);

    UserFoodDto updateUserFood(UserFoodUpdateDto userFoodUpdateDto);

    List<UserFoodDto> getUserFoodByUserId(Long id);
}
