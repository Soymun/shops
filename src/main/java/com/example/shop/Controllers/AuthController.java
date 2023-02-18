package com.example.shop.Controllers;


import com.example.shop.DTO.Security.LoginDTO;
import com.example.shop.DTO.Security.RegDTO;
import com.example.shop.Entity.Role;
import com.example.shop.Entity.User;
import com.example.shop.Jwt.JwtTokenProvider;
import com.example.shop.Service.Imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {
    private final UserServiceImp userServiceImp;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthController(UserServiceImp userServiceImp, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userServiceImp = userServiceImp;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/registration_user")
    public ResponseEntity<?> registration(@RequestBody RegDTO regDTO){
        User user = userServiceImp.findUserByEmail(regDTO.getEmail());
        if(user != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        user = new User();
        user.setPassword(passwordEncoder.encode(regDTO.getPassword()));
        user.setEmail(regDTO.getEmail());
        user.setUsername(regDTO.getName());
        user.setRole(Role.Buyer);
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            User user = userServiceImp.findUserByEmail(loginDTO.getEmail());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
            String token = jwtTokenProvider.createToken(user.getEmail(), user.getRole());
            Map<String, String> map = new HashMap<>();
            map.put("email", user.getEmail());
            map.put("token", token);
            return ResponseEntity.ok(map);
        }catch (AuthenticationException e){
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/registration_selman")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> regSelman(){
//        User user = userServiceImp.findUserByEmail(regDTO.getEmail());
//        if(user == null){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        user.setRole(Role.Salesman);
        return null;
    }
}
