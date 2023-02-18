package com.example.shop.DTO.Oder;

import com.example.shop.Entity.Status;
import lombok.Data;

@Data
public class OrderUpdateDto {

    private Long id;

    private Status status;
}
