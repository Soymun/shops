package com.example.shop.DTO.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserCreateDto {
    private String email;

    private String password;

    private String username;
}
