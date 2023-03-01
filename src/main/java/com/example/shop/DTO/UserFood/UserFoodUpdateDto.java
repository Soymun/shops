package com.example.shop.DTO.UserFood;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserFoodUpdateDto {

    private Long id;

    private Long count;

    private Boolean visible;
}
