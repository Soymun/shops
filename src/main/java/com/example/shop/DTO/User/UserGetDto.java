package com.example.shop.DTO.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserGetDto {

    private Long id;

    private String username;

    private Long rating;
}
