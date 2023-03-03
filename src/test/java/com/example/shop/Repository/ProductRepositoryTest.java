package com.example.shop.Repository;

import com.example.shop.Config.SystemJpaTest;
import com.example.shop.Entity.Product;
import com.example.shop.Entity.TypeOfWeight;
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
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        SQLStatementCountValidator.reset();
    }

    @DisplayName("Получение root типов еды")
    @Test
    @Rollback
    @Sql({"classpath:sql/1_clear_schema.sql",
            "classpath:sql/3_insert_type_of_food.sql",
            "classpath:sql/4_insert_product.sql"
    })
    void findProductById() {
        //when

        Product product = productRepository.findProductById(1001L).orElse(new Product());
        //then

        assertEquals(product.getId(), 1001);
        assertEquals(product.getName(), "test");
        assertEquals(product.getTypeOfFoodId(), 1001);
    }

    @DisplayName("Получение root типов еды")
    @Test
    @Rollback
    @Sql({"classpath:sql/1_clear_schema.sql",
            "classpath:sql/3_insert_type_of_food.sql",
            "classpath:sql/4_insert_product.sql"
    })
    void findProductsByTypeOfFoodIdOrderByPrice() {

        //when

        List<Product> product = productRepository.findProductsByTypeOfFoodIdOrderByPrice(1001L);
        //then

        assertEquals(product.size(), 1);
        assertEquals(product.get(0).getId(), 1001);
        assertEquals(product.get(0).getName(), "test");
    }

    @DisplayName("Получение root типов еды")
    @Test
    @Rollback
    @Sql({"classpath:sql/1_clear_schema.sql",
            "classpath:sql/3_insert_type_of_food.sql"
    })
    void saveProduct() {
        //given
        Product product = new Product();
        product.setName("test");
        product.setTypeOfFoodId(1001L);
        product.setAbout("test");
        product.setTypeOfWeight(TypeOfWeight.GRAM);

        //when
        Product savedProduct = productRepository.save(product);

        //then
        assertNotNull(savedProduct.getId());
        assertEquals(savedProduct.getName(), product.getName());
    }
}