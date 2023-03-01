package com.example.shop.DTO.Oder;

import com.example.shop.Entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderUpdateDto {

    private Long id;

    private Status status;
}
