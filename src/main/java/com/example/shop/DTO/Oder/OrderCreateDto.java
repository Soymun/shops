package com.example.shop.DTO.Oder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderCreateDto {

    private Long userId;

    private Long shopId;
}
