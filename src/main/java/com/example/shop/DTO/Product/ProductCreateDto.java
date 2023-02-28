package com.example.shop.DTO.Product;

import com.example.shop.Entity.TypeOfWeight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductCreateDto {

    private String name;

    private String about;

    private Double weight;

    private TypeOfWeight typeOfWeight;

    private Long typeOfFoodId;

    private Double calories;

    private Long price;
}
