package com.example.shop.Repository;

import com.example.shop.Entity.Order;
import com.example.shop.Entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> getOrdersByUserIdOrderByCreateOrderDesc(Long id);

    List<Order> getOrdersByShopIdAndStatusOrderByCreateOrder(Long shopId, Status status);
}
