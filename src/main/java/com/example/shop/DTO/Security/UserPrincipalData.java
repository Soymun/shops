package com.example.shop.DTO.Security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Data
@Component
@AllArgsConstructor
@RequiredArgsConstructor
public class UserPrincipalData {

    private Long id;

    private String userName;
}
