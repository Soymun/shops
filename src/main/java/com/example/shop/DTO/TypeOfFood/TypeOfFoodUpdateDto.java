package com.example.shop.DTO.TypeOfFood;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TypeOfFoodUpdateDto {

    private Long id;

    private String name;

    private Long typeOfFoodId;
}
