package com.example.shop.DTO.UserFood;

import com.example.shop.DTO.Product.ProductDTO;
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

    public UserFoodDto(Long id, Long userId, Long productId, Long orderId, Long count) {
        this.id = id;
        this.userId = userId;
        this.productDTO = new ProductDTO();
        productDTO.setId(productId);
        this.orderId = orderId;
        this.count = count;
    }
}
