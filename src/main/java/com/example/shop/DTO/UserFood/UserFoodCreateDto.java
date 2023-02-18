package com.example.shop.DTO.UserFood;

import lombok.Data;

@Data
public class UserFoodCreateDto {

    private Long userId;

    private Long productId;

    private Long count;
}
