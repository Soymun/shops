package com.example.shop.DTO.User;

import lombok.Data;

@Data
public class UserCreateDto {
    private String email;

    private String password;

    private String username;
}
