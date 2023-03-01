package com.example.shop.Service.Imp;

import com.example.shop.DTO.Security.PersonDetails;
import com.example.shop.DTO.User.UserCreateDto;
import com.example.shop.DTO.User.UserDto;
import com.example.shop.DTO.User.UserUpdateDto;
import com.example.shop.Entity.Role;
import com.example.shop.Entity.User;
import com.example.shop.Exception.ActivateIsFalse;
import com.example.shop.Exception.NoAccessOperation;
import com.example.shop.Exception.NoFoundException;
import com.example.shop.DTO.Security.UserPrincipalData;
import com.example.shop.Mappers.UserMapper;
import com.example.shop.Repository.UserRepository;
import com.example.shop.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserPrincipalData userPrincipalData;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByEmail(username);
        return new PersonDetails(user.getId(), user.getEmail(), user.getPassword(), user.getRole());
    }

    @Override
    public UserDto getUserById(Long id) {
        log.info("Выдача пользователя с id {}", id);
        if(!Objects.equals(id, userPrincipalData.getId())){
            throw new NoAccessOperation("Операция обновления запрещена");
        }
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
        return userRepository.findUserByEmailAndActivatedIsTrue(email).orElseThrow(() -> {
            throw new NoFoundException("Пользователь не был найден");
        });
    }

    @Override
    public UserDto updateUser(UserUpdateDto user) {
        log.info("Изменение пользователя с id {}", user.getId());
        User findsUser = userRepository.findUserById(user.getId()).orElseThrow(() -> {
            throw new NoFoundException("Пользователь не был найден");
        });
        if(!Objects.equals(findsUser.getId(), userPrincipalData.getId())){
            throw new NoAccessOperation("Операция обновления запрещена");
        }
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
        if(user.getMailingList() != null){
            findsUser.setMailingList(user.getMailingList());
        }
        return userMapper.userToUserDto(userRepository.save(findsUser));
    }

    @Override
    public String saveUser(UserCreateDto user) {
        log.info("Сохранение пользователя");
        User user1 = new User();
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setUsername(user.getUsername());
        user1.setRole(Role.Buyer);
        user1.setRating(0L);
        user1.setBalls(0L);
        user1.setMailingList(true);
        user1.setActivated(false);
        user1.setUuid(UUID.randomUUID().toString());
        userRepository.save(user1);
        return user1.getUuid();
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        if(!Objects.equals(id, userPrincipalData.getId())){
            throw new NoAccessOperation("Операция обновления запрещена");
        }
        log.info("Удаление пользователя с id {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public boolean foundTheUserByEmail(String email) {
        log.info("Проверка пользователя");
        return userRepository.findUserByEmail(email).isPresent();
    }

    @Override
    public boolean activateUser(String uuid) {
        User user = userRepository.getUserByUuid(uuid).orElseThrow(() -> {throw new ActivateIsFalse("Ошибка активации");});

        user.setUuid(null);
        user.setActivated(true);
        userRepository.save(user);
        return true;
    }
}
