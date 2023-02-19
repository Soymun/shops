package com.example.shop.Repository;

import com.example.shop.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> getOrdersByUserId(Long id);
}
