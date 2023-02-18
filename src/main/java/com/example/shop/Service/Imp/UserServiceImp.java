package com.example.shop.Service.Imp;

import com.example.shop.DTO.User.UserCreateDto;
import com.example.shop.DTO.User.UserDto;
import com.example.shop.DTO.User.UserUpdateDto;
import com.example.shop.Entity.User;
import com.example.shop.Repository.UserRepository;
import com.example.shop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByEmail(username);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getRole().simpleGrantedAuthorities());
    }

    @Override
    public UserDto getUserById(Long id) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElse(null);
    }

    @Override
    public UserDto updateUser(UserUpdateDto user) {
        return null;
    }

    @Override
    public void saveUser(UserCreateDto user) {

    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public boolean foundTheUserByEmail(String email) {
        return false;
    }
}
