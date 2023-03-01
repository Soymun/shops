package com.example.shop.DTO.UserFood;

import com.example.shop.DTO.Product.ProductGetDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserFoodDto {

    private Long id;

    private Long userId;

    private ProductGetDto product;

    private Long orderId;

    private Long count;
}
