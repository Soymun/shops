package com.example.shop.Repository;

import com.example.shop.Entity.TypeOfFood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeOfFoodRepository extends JpaRepository<TypeOfFood, Long> {

    List<TypeOfFood> getTypeOfFoodsByTypeOfFoodId(Long id);

    List<TypeOfFood> getTypeOfFoodsByTypeOfFoodIdIsNull();
}
