package com.example.shop.Facade;

import com.example.shop.DTO.UserFood.UserFoodCreateDto;
import com.example.shop.DTO.UserFood.UserFoodDto;
import com.example.shop.DTO.UserFood.UserFoodUpdateDto;
import com.example.shop.Service.Imp.UserFoodServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserFoodFacade {

    private final UserFoodServiceImp userFoodServiceImp;


    public void createUserFood(UserFoodCreateDto userFoodCreateDto){
        userFoodServiceImp.createUserFood(userFoodCreateDto);
    }

    public void deleteUserFoodById(Long id){
        userFoodServiceImp.deleteUserFoodById(id);
    }

    public UserFoodDto updateUserFood(UserFoodUpdateDto userFoodUpdateDto){
        return userFoodServiceImp.updateUserFood(userFoodUpdateDto);
    }
    public UserFoodDto getUserFoodById(Long id){
        return userFoodServiceImp.getUserFoodById(id);
    }

    public List<UserFoodDto> getUserFoodByUserId(Long id) {
        return userFoodServiceImp.getUserFoodByUserId(id);
    }

    public List<UserFoodDto> getUserFoodByOrderId(Long id){
        return userFoodServiceImp.getUserFoodByOrderId(id);

    }
}
