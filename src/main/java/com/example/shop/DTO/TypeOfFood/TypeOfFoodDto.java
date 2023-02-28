package com.example.shop.DTO.TypeOfFood;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TypeOfFoodDto {

    private Long id;

    private String name;

    private String urlToPhoto;

    private Long typeOfFoodId;

    public TypeOfFoodDto(Long id) {
        this.id = id;
    }
}
