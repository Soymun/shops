package com.example.shop.Repository;

import com.example.shop.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findProductById(Long aLong);

    List<Product> findProductsByTypeOfFoodIdOrderByPrice(Long id);
}
