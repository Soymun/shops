package com.example.shop.DTO.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductUpdateDto {

    private Long id;

    private String name;

    private String about;

    private Double weight;

    private Double calories;

    private Long price;

    private Boolean inBallsProgram;

    private Long ballsPrice;
}
