package com.example.shop.DTO.TypeOfFood;

import lombok.Data;

@Data
public class TypeOfFoodCreateDto {

    private String name;

    private String urlToPhoto;

    private Long typeOfFoodId;
}
