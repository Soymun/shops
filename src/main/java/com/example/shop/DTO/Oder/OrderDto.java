package com.example.shop.DTO.Oder;

import com.example.shop.DTO.UserFood.UserFoodDto;
import com.example.shop.Entity.Status;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {

    private Long id;

    private Long userId;

    private Status status;

    private List<UserFoodDto> userFoodDtos;
}
