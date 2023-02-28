package com.example.shop.DTO.UserFood;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserFoodCreateDto {

    private Long userId;

    private Long productId;

    private Long count;
}
