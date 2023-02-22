package com.example.shop.Service.Imp;

import com.example.shop.DTO.User.UserCreateDto;
import com.example.shop.DTO.User.UserDto;
import com.example.shop.DTO.User.UserUpdateDto;
import com.example.shop.Entity.Role;
import com.example.shop.Entity.User;
import com.example.shop.Exception.NoFoundException;
import com.example.shop.Mappers.UserMapper;
import com.example.shop.Repository.UserRepository;
import com.example.shop.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByEmail(username);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getRole().simpleGrantedAuthorities());
    }

    @Override
    public UserDto getUserById(Long id) {
        log.info("Выдача пользователя с id {}", id);
        return userMapper
                .userToUserDto(
                        userRepository
                                .findUserById(id)
                                .orElseThrow(() -> {
                                    throw new NoFoundException("Пользователь не был найден");
                                }));
    }

    @Override
    public User findUserByEmail(String email) {
        log.info("Выдача пользователя с email {}", email);
        return userRepository.findUserByEmail(email).orElseThrow(() -> {
            throw new NoFoundException("Пользователь не был найден");
        });
    }

    @Override
    public UserDto updateUser(UserUpdateDto user) {
        log.info("Изменение пользователя с id {}", user.getId());
        User findsUser = userRepository.findUserById(user.getId()).orElseThrow(() -> {
            throw new NoFoundException("Пользователь не был найден");
        });
        if (user.getEmail() != null) {
            findsUser.setUsername(user.getEmail());
        }
        if (user.getPassword() != null) {
            findsUser.setPassword(user.getPassword());
        }
        if (user.getUsername() != null) {
            findsUser.setUsername(user.getUsername());
        }
        if (user.getRole() != null) {
            findsUser.setRole(user.getRole());
        }
        if (user.getBalls() != null) {
            findsUser.setBalls(user.getBalls());
        }
        if (user.getRating() != null) {
            findsUser.setRating(user.getRating());
        }
        return userMapper.userToUserDto(userRepository.save(findsUser));
    }

    @Override
    public void saveUser(UserCreateDto user) {
        log.info("Сохранение пользователя");
        User user1 = new User();
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setUsername(user.getUsername());
        user1.setRole(Role.Buyer);
        user1.setRating(0L);
        user1.setBalls(0L);
        userRepository.save(user1);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        log.info("Удаление пользователя с id {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public boolean foundTheUserByEmail(String email) {
        log.info("Проверка пользователя");
        return userRepository.findUserByEmail(email).isPresent();
    }
}
