package com.example.shop.Service;

import com.example.shop.DTO.User.UserCreateDto;
import com.example.shop.DTO.User.UserDto;
import com.example.shop.DTO.User.UserUpdateDto;
import com.example.shop.Entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    UserDto getUserById(Long id);

    User findUserByEmail(String email);


    UserDto updateUser(UserUpdateDto user);

    void saveUser(UserCreateDto user);

    void deleteUser(Long id);

    boolean foundTheUserByEmail(String email);
}
