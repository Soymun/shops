package com.example.shop.DTO.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserCreateDto {
    private String email;

    private String password;

    private String username;
}
