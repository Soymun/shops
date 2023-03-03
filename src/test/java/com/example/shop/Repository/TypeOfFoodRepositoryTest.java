package com.example.shop.Repository;

import com.example.shop.Config.SystemJpaTest;
import com.example.shop.Entity.TypeOfFood;
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
class TypeOfFoodRepositoryTest {

    @Autowired
    private TypeOfFoodRepository typeOfFoodRepository;

    @BeforeEach
    void setUp() {
        SQLStatementCountValidator.reset();
    }

    @DisplayName("Получение под root типов еды")
    @Test
    @Rollback
    @Sql({"classpath:sql/1_clear_schema.sql",
            "classpath:sql/3_insert_type_of_food.sql"
    })
    void getTypeOfFoodsByTypeOfFoodId() {
        //when
        List<TypeOfFood> list = typeOfFoodRepository.getTypeOfFoodsByTypeOfFoodId(1001L);
        //then
        assertEquals(list.size(), 1);
        assertEquals(list.get(0).getId(), 1002L);
    }

    @DisplayName("Получение root типов еды")
    @Test
    @Rollback
    @Sql({"classpath:sql/1_clear_schema.sql",
            "classpath:sql/3_insert_type_of_food.sql"
    })
    void getTypeOfFoodsByTypeOfFoodIdIsNull() {
        //when
        List<TypeOfFood> list = typeOfFoodRepository.getTypeOfFoodsByTypeOfFoodIdIsNull();

        //then
        assertEquals(list.size(), 1);
        assertEquals(list.get(0).getId(), 1001L);
    }

    @DisplayName("Сохранение типа еды")
    @Test
    @Rollback
    @Sql({"classpath:sql/1_clear_schema.sql"})
    void saveTypeOfFood() {
        //given
        TypeOfFood typeOfFood = new TypeOfFood();
        typeOfFood.setName("test");
        typeOfFood.setUrlToPhoto("test");

        //when
        TypeOfFood savedTOF = typeOfFoodRepository.save(typeOfFood);

        //then
        assertNotNull(savedTOF.getId());
        assertEquals(savedTOF.getName(), typeOfFood.getName());
    }
}