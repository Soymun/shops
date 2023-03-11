package com.example.shop.Facade;

import com.example.shop.DTO.Oder.OrderCreateDto;
import com.example.shop.DTO.Oder.OrderDto;
import com.example.shop.DTO.Oder.OrderUpdateDto;
import com.example.shop.Service.Imp.OrderServiceImpl;
import com.example.shop.Service.Imp.UserFoodServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderServiceImpl orderService;

    private final UserFoodServiceImp userFoodServiceImp;

    public void createOrder(OrderCreateDto orderCreateDto){
        userFoodServiceImp.setAllUserFoodUnVisible(orderCreateDto.getUserId(), orderService.createOrder(orderCreateDto));
    }

    public void deleteOrderById(Long id){
        orderService.deleteOrderById(id);
    }

    public OrderDto getOrderById(Long id){
        return orderService.getOrderById(id);
    }

    public List<OrderDto> getOrdersByUserId(Long id){
        return orderService.getOrdersByUserId(id);
    }

    public OrderDto updateOrder(OrderUpdateDto orderUpdateDto){
        return orderService.updateOrder(orderUpdateDto);
    }

    public List<OrderDto> getOrderByShopId(Long id){
        return orderService.getOrderByShopId(id);
    }
}
