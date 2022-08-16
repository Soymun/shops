package com.example.shop.Repository;

import com.example.shop.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findUserById(Long aLong);

    Optional<User> findUserByEmail(String email);
}
