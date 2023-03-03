package com.example.shop.Repository;

import com.example.shop.Config.SystemJpaTest;
import com.example.shop.Entity.Order;
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
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        SQLStatementCountValidator.reset();
    }

    @DisplayName("Поиск заказов по id пользователя")
    @Test
    @Sql({"classpath:sql/1_clear_schema.sql",
            "classpath:sql/2_insert_person.sql",
            "classpath:sql/3_insert_type_of_food.sql",
            "classpath:sql/4_insert_product.sql",
            "classpath:sql/6_insert_order.sql",
            "classpath:sql/5_insert_user_food.sql"
    })
    @Rollback
    void getOrdersByUserIdOrderByCreateOrderDesc() {
        //when
        List<Order> list = orderRepository.getOrdersByUserIdOrderByCreateOrderDesc(1001L);

        //then
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).getId(), 1001);
    }
}