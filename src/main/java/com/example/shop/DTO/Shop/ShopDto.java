package com.example.shop.DTO.Shop;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ShopDto {

    private Long id;

    private String city;

    private String street;

    private String house;

    private String numberHouse;

    private Double cordX;

    private Double cordY;

    private LocalTime open;

    private LocalTime close;

    private String phone;
}
