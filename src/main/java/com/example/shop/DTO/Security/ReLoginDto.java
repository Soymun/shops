package com.example.shop.DTO.Security;

import com.example.shop.Entity.Role;
import lombok.Data;

@Data
public class ReLoginDto {

    private String token;

    private String email;

    private Role role;

    private boolean isInvalid;
}
