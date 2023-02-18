package com.example.shop.DTO.TypeOfFood;

import lombok.Data;

@Data
public class TypeOfFoodUpdateDto {

    private Long id;

    private String name;

    private String urlToPhoto;

    private Long typeOfFoodId;
}
