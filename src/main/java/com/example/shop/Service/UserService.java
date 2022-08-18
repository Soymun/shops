package com.example.shop.Service;

import com.example.shop.Entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;


public interface UserService extends UserDetailsService {

    @Transactional
    User findUserById(Long id);

    @Transactional
    User findUserByEmail(String email);

    @Transactional
    boolean updateUser(User user);

}
