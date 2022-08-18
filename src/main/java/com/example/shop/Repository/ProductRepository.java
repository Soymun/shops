package com.example.shop.Repository;

import com.example.shop.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductById(Long aLong);
}
