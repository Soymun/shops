package com.example.shop.DTO.User;

import com.example.shop.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    private String email;

    private Long balls;

    private String username;

    private Role role;

    private Long rating;

    private Boolean mailingList;
}
