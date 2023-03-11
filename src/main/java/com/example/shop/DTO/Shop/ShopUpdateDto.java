package com.example.shop.DTO.Shop;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalTime;

@Data
public class ShopUpdateDto {

    private Long id;

    private String city;

    private String street;

    private String house;

    private String numberHouse;

    private Double cordX;

    private Double cordY;

    @JsonFormat(pattern="HH:mm:ss")
    private LocalTime open;

    @JsonFormat(pattern="HH:mm:ss")
    private LocalTime close;

    private String phone;
}
