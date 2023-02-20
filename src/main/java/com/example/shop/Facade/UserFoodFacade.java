package com.example.shop.Facade;

import com.example.shop.DTO.UserFood.UserFoodCreateDto;
import com.example.shop.DTO.UserFood.UserFoodDto;
import com.example.shop.DTO.UserFood.UserFoodUpdateDto;
import com.example.shop.Service.Imp.ProductServiceImp;
import com.example.shop.Service.Imp.UserFoodServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserFoodFacade {

    private final UserFoodServiceImp userFoodServiceImp;

    private final ProductServiceImp productServiceImp;


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
        UserFoodDto userFoodDto = userFoodServiceImp.getUserFoodById(id);
        userFoodDto.setProductDTO(productServiceImp.getProductById(userFoodDto.getProductDTO().getId()));
        return userFoodDto;
    }

    public List<UserFoodDto> getUserFoodByUserId(Long id) {
        return userFoodServiceImp.getUserFoodByUserId(id).stream().peek(n -> n.setProductDTO(productServiceImp.getProductById(n.getProductDTO().getId()))).toList();
    }

    public List<UserFoodDto> getUserFoodByOrderId(Long id){
        return userFoodServiceImp.getUserFoodByOrderId(id).stream().peek(n -> n.setProductDTO(productServiceImp.getProductById(n.getProductDTO().getId()))).toList();

    }
}
