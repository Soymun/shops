package com.example.shop.Controllers;


import com.example.shop.DTO.Security.LoginDTO;
import com.example.shop.DTO.Security.ReLoginDto;
import com.example.shop.DTO.Security.RegDTO;
import com.example.shop.Facade.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationFacade authenticationFacade;

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody RegDTO regDTO){
        return authenticationFacade.registration(regDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        return authenticationFacade.login(loginDTO);
    }

    @PostMapping("/relogin")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> regSelman(@RequestBody ReLoginDto reLoginDto){
        return authenticationFacade.reLogin(reLoginDto);
    }
}
