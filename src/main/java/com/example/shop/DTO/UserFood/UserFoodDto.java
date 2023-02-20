package com.example.shop.DTO.UserFood;

import com.example.shop.DTO.Product.ProductDTO;
import com.example.shop.DTO.TypeOfFood.TypeOfFoodDto;
import com.example.shop.Entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserFoodDto {

    private Long id;

    private Long userId;

    private ProductDTO productDTO;

    private Long orderId;

    private Long count;

    public UserFoodDto(Long id, Long userId, Product product, Long orderId, Long count) {
        this.id = id;
        this.userId = userId;
        this.productDTO = new ProductDTO(product.getId(), product.getName(), product.getAbout(), product.getWeight(), product.getTypeOfWeight(), new TypeOfFoodDto(product.getTypeOfFoodId()), product.getCalories(), product.getPrice(), product.getUrlToPngFile(), product.isInBallsProgram(), product.getBallsPrice());
        this.orderId = orderId;
        this.count = count;
    }
}
