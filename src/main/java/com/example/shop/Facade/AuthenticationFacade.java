package com.example.shop.Facade;


import com.example.shop.DTO.Security.LoginDTO;
import com.example.shop.DTO.Security.ReLoginDto;
import com.example.shop.DTO.Security.RegDTO;
import com.example.shop.DTO.User.UserCreateDto;
import com.example.shop.DTO.email.ContextPage;
import com.example.shop.DTO.email.EmailContext;
import com.example.shop.Entity.User;
import com.example.shop.Exception.FoundException;
import com.example.shop.Exception.MyAuthenticationException;
import com.example.shop.Jwt.JwtTokenProvider;
import com.example.shop.Response.ResponseDto;
import com.example.shop.Service.Imp.DefaultEmailService;
import com.example.shop.Service.Imp.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthenticationFacade {

    private final UserServiceImp userServiceImp;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    private final DefaultEmailService defaultEmailService;
    public ResponseEntity<?> registration(RegDTO regDTO) throws MessagingException {
        if(userServiceImp.foundTheUserByEmail(regDTO.getEmail())){
            throw new FoundException("Пользователь с таким email уже был зарегестрирован!!!");
        }
        UserCreateDto userCreateDto = new UserCreateDto(regDTO.getEmail(), passwordEncoder.encode(regDTO.getPassword()), regDTO.getName());
        String uuid = userServiceImp.saveUser(userCreateDto);
        defaultEmailService.sendSimpleEmail(new EmailContext("Link your email", "123", "321", "en", "registration", List.of(new ContextPage("ref",String.format("http://localhost:8080/activate/%s", uuid)), new ContextPage("text", userCreateDto.getUsername()))), userCreateDto.getEmail());
        return ResponseEntity.status(201).build();
    }

    public ResponseEntity<?> login(LoginDTO loginDTO) {
        try {
            User user = userServiceImp.findUserByEmail(loginDTO.getEmail());
            String token = jwtTokenProvider.createToken(user.getEmail(), user.getRole());
            Map<String, Object> map = new HashMap<>();
            map.put("id", user.getId());
            map.put("role", user.getRole());
            map.put("token", token);
            return ResponseEntity.ok(ResponseDto.builder().data(map).build());
        }catch (AuthenticationException e){
            throw new MyAuthenticationException("Ошибка аунтефикации");
        }
    }

    public ResponseEntity<?> reLogin(ReLoginDto reLoginDto){
        if(reLoginDto.isInvalid()){
            return ResponseEntity.ok(ResponseDto.builder().data(jwtTokenProvider.createToken(jwtTokenProvider.getEmail(reLoginDto.getToken()), reLoginDto.getRole())).build());
        }
        else {
            if(jwtTokenProvider.validateToken(reLoginDto.getToken())){
                return ResponseEntity.ok(ResponseDto.builder().data(jwtTokenProvider.createToken(jwtTokenProvider.getEmail(reLoginDto.getToken()), reLoginDto.getRole())).build());
            }
            else {
                return ResponseEntity.noContent().build();
            }
        }
    }

    public void activate(String uuid){
        userServiceImp.activateUser(uuid);
    }
}
