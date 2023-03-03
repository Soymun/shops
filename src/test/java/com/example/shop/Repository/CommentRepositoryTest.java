package com.example.shop.Repository;

import com.example.shop.Config.SystemJpaTest;
import com.example.shop.Entity.Comment;
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
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        SQLStatementCountValidator.reset();
    }

    @DisplayName("Поиск коментариев по пользователю")
    @Test
    @Sql({"classpath:sql/1_clear_schema.sql",
            "classpath:sql/2_insert_person.sql",
            "classpath:sql/3_insert_type_of_food.sql",
            "classpath:sql/4_insert_product.sql",
            "classpath:sql/6_insert_order.sql",
            "classpath:sql/5_insert_user_food.sql",
            "classpath:sql/7_insert_comment.sql"
    })
    @Rollback
    void getCommentsByUserIdOrderByLocalDateTime() {
        //when
        List<Comment> list = commentRepository.getCommentsByUserIdOrderByLocalDateTime(1001L);

        //then
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).getId(), 1002);
    }

    @DisplayName("Поиск коментариев по заказу")
    @Test
    @Sql({"classpath:sql/1_clear_schema.sql",
            "classpath:sql/2_insert_person.sql",
            "classpath:sql/3_insert_type_of_food.sql",
            "classpath:sql/4_insert_product.sql",
            "classpath:sql/6_insert_order.sql",
            "classpath:sql/5_insert_user_food.sql",
            "classpath:sql/7_insert_comment.sql"
    })
    @Rollback
    void getCommentsByProductIdOrderByLocalDateTime() {
        //when
        List<Comment> list = commentRepository.getCommentsByProductIdOrderByLocalDateTime(1001L);

        //then
        assertEquals(list.size(), 1);
        assertEquals(list.get(0).getId(), 1001);
    }
}