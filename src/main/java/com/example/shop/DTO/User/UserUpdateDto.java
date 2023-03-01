package com.example.shop.DTO.User;

import com.example.shop.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserUpdateDto {

    private Long id;

    private String email;

    private String password;

    private Long balls;

    private String username;

    private Role role;

    private Long rating;

    private Boolean mailingList;
}
