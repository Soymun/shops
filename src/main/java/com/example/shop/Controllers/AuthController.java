package com.example.shop.Controllers;


import com.example.shop.DTO.Security.LoginDTO;
import com.example.shop.DTO.Security.ReLoginDto;
import com.example.shop.DTO.Security.RegDTO;
import com.example.shop.Facade.AuthenticationFacade;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins="http://localhost:3000")
public class AuthController {
    private final AuthenticationFacade authenticationFacade;

    @ApiOperation(value = "Метод для регистрации пользователей.", notes = "Возвращает код 201")
    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody RegDTO regDTO){
        return authenticationFacade.registration(regDTO);
    }

    @ApiOperation(value = "Метод для логина.", notes = "Возвращает мапу с токеном, id, и ролью.")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        return authenticationFacade.login(loginDTO);
    }

    @ApiOperation(value = "Метод для релогина.", notes = "Boolean значение нужно для аунтификации после того, как токен умрёт. Возвращает токен")
    @PostMapping("/relogin")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> regSelman(@RequestBody ReLoginDto reLoginDto){
        return authenticationFacade.reLogin(reLoginDto);
    }

    @GetMapping("/activate/{uuid}")
    public ResponseEntity<?> activate(@PathVariable String uuid){
        return authenticationFacade.activate(uuid);
    }
}
