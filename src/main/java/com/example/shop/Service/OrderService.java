package com.example.shop.Service;


import com.example.shop.DTO.Oder.OrderCreateDto;
import com.example.shop.DTO.Oder.OrderDto;
import com.example.shop.DTO.Oder.OrderUpdateDto;

import java.util.List;

public interface OrderService {


    Long createOrder(OrderCreateDto orderCreateDto);

    void deleteOrderById(Long id);

    OrderDto getOrderById(Long id);

    List<OrderDto> getOrdersByUserId(Long id);

    OrderDto updateOrder(OrderUpdateDto orderUpdateDto);
}
