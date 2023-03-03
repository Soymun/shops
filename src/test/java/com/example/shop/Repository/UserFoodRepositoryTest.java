package com.example.shop.Repository;

import com.example.shop.Config.SystemJpaTest;
import com.example.shop.Entity.UserProduct;
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
class UserFoodRepositoryTest {

    @Autowired
    private UserFoodRepository userFoodRepository;

    @BeforeEach
    void setUp() {
        SQLStatementCountValidator.reset();
    }


    @DisplayName("Поиск еды пользователя, для которых не был создан заказ")
    @Test
    @Sql({"classpath:sql/1_clear_schema.sql",
            "classpath:sql/2_insert_person.sql",
            "classpath:sql/3_insert_type_of_food.sql",
            "classpath:sql/4_insert_product.sql",
            "classpath:sql/6_insert_order.sql",
            "classpath:sql/5_insert_user_food.sql"
    })
    @Rollback
    void getUserProductsByUserIdAndVisibleIsTrue() {
        //when
        List<UserProduct> list = userFoodRepository.getUserProductsByUserIdAndVisibleIsTrue(1001L);

        //then
        assertEquals(list.size(), 1);
        assertEquals(list.get(0).getId(), 1001);
    }

    @DisplayName("Поиск еды по id заказа")
    @Test
    @Sql({"classpath:sql/1_clear_schema.sql",
            "classpath:sql/2_insert_person.sql",
            "classpath:sql/3_insert_type_of_food.sql",
            "classpath:sql/4_insert_product.sql",
            "classpath:sql/6_insert_order.sql",
            "classpath:sql/5_insert_user_food.sql"
    })
    @Rollback
    void getUserProductsByOrderId() {
        //when
        List<UserProduct> list = userFoodRepository.getUserProductsByOrderId(1001L);

        //then
        assertEquals(list.size(), 1);
        assertEquals(list.get(0).getId(), 1003);
    }
}