package com.example.shop.Repository;

import com.example.shop.Config.SystemJpaTest;
import com.example.shop.Entity.Role;
import com.example.shop.Entity.User;
import com.vladmihalcea.sql.SQLStatementCountValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SystemJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        SQLStatementCountValidator.reset();
    }

    @DisplayName("Поиск пользователя по id")
    @Sql({"classpath:sql/1_clear_schema.sql",
            "classpath:sql/2_insert_person.sql"
    })
    @Test
    @Rollback
    void findUserById() {

        //when
        User findUser = userRepository.findUserById(1001L).orElse(new User());

        //then
        assertEquals(findUser.getId(), 1001);
        assertEquals(findUser.getEmail(), "test1");
        assertEquals(findUser.getRole(), Role.Salesman);
    }

    @DisplayName("Получение пользователя по email")
    @Test
    @Sql({"classpath:sql/1_clear_schema.sql",
            "classpath:sql/2_insert_person.sql"
    })
    @Rollback
    void findUserByEmail() {

        //when
        User user = userRepository.findUserByEmail("test2").orElse(new User());

        //then
        assertEquals(user.getId(), 1002);
        assertEquals(user.getEmail(), "test2");
        assertEquals(user.getRole(), Role.Buyer);
    }

    @DisplayName("Получение активированного пользователя по email")
    @Test
    @Sql({"classpath:sql/1_clear_schema.sql",
            "classpath:sql/2_insert_person.sql"
    })
    @Rollback
    void findUserByEmailAndActivatedIsTrue() {
        //when
        User user = userRepository.findUserByEmailAndActivatedIsTrue("test2").orElse(new User());

        //then
        assertEquals(user.getId(), 1002);
        assertEquals(user.getEmail(), "test2");
        assertEquals(user.getRole(), Role.Buyer);
    }

    @DisplayName("Получение неактивированного пользователя по email")
    @Test
    @Sql({"classpath:sql/1_clear_schema.sql",
            "classpath:sql/2_insert_person.sql"
    })
    @Rollback
    void findUserByEmailAndActivatedIsFalse() {
        //when
        User user = userRepository.findUserByEmailAndActivatedIsTrue("test3").orElse(new User());

        //then
        assertNull(user.getId());
        assertNull(user.getEmail());
        assertNull(user.getRole());
    }

    @DisplayName("Поиск пользователя по рассылке")
    @Test
    @Sql({"classpath:sql/1_clear_schema.sql",
            "classpath:sql/2_insert_person.sql"
    })
    @Rollback
    void getUsersByMailingListIsTrue() {
        //when
        List<User> users = userRepository.getUsersByMailingListIsTrue();

        //then
        assertEquals(users.size(), 1);
        assertEquals(users.get(0).getEmail(), "test2");
    }

    @DisplayName("Поиск пользователя по uuid")
    @Test
    @Sql({"classpath:sql/1_clear_schema.sql",
            "classpath:sql/2_insert_person.sql"
    })
    @Rollback
    void getUserByUuid() {
        //when
        User user = userRepository.getUserByUuid("test").orElse(new User());

        //then
        assertEquals(user.getId(), 1003);
        assertEquals(user.getEmail(), "test3");
        assertEquals(user.getRole(), Role.Salesman);
    }

    @DisplayName("Сохранение пользователя")
    @Test
    @Sql({"classpath:sql/1_clear_schema.sql",
            "classpath:sql/2_insert_person.sql"
    })
    @Rollback
    void saveUser() {
        //given
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setEmail("test");
        user.setActivated(true);
        user.setRating(0L);
        user.setBalls(0L);
        user.setMailingList(true);

        //when
        User savedUser = userRepository.save(user);

        //then
        assertNotNull(savedUser.getId());
        assertEquals(savedUser.getEmail(), user.getEmail());
        assertEquals(savedUser.getUsername(), user.getUsername());
    }
}