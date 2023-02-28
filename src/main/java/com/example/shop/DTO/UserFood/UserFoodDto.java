package com.example.shop.DTO.UserFood;

import com.example.shop.DTO.Product.ProductDTO;
import com.example.shop.Entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserFoodDto {

    private Long id;

    private Long userId;

    private ProductDTO product;

    private Long orderId;

    private Long count;
}
