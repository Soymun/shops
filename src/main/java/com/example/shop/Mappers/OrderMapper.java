package com.example.shop.Mappers;

import com.example.shop.DTO.Oder.OrderCreateDto;
import com.example.shop.DTO.Oder.OrderDto;
import com.example.shop.Entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto orderToOrderDto(Order order);

    Order orderCreateDtoToOrder(OrderCreateDto orderCreateDto);
}
