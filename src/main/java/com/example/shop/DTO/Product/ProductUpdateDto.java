package com.example.shop.DTO.Product;

import lombok.Data;

@Data
public class ProductUpdateDto {

    private Long id;

    private String name;

    private String about;

    private Double weight;

    private Double calories;

    private Long price;

    private String urlToPngFile;

    private Boolean inBallsProgram;

    private Long ballsPrice;
}
