package com.example.shop.DTO.Oder;

import com.example.shop.DTO.UserFood.UserFoodDto;
import com.example.shop.Entity.Status;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {

    private Long id;

    private Long userId;

    private Status status;

    private LocalDateTime localDateTime;

    private List<UserFoodDto> userFoodDtos;
}
