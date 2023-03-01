package com.example.shop.DTO.Product;


import com.example.shop.Entity.TypeOfWeight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductGetDto {

    private Long id;

    private String name;

    private String about;

    private Double weight;

    private TypeOfWeight typeOfWeight;

    private Double calories;

    private Long price;

    private String urlToPngFile;
}
