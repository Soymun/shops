package com.example.shop.Service;

import com.example.shop.Entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;


public interface UserService extends UserDetailsService {

    @Transactional
    public User findUserById(Long id);

    @Transactional
    public User findUserByEmail(String email);

    @Transactional
    public boolean updateUser(User user);

}
