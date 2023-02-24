package com.example.shop.DTO.User;

import com.example.shop.Entity.Role;
import lombok.Data;

@Data
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
