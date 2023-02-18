package com.example.shop.DTO.UserFood;

import lombok.Data;

@Data
public class UserFoodDto {

    private Long id;

    private Long userId;

    private Long productId;

    private Long count;

    private boolean visible;
}
