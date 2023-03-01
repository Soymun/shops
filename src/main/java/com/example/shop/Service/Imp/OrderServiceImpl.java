package com.example.shop.Service.Imp;

import com.example.shop.DTO.Oder.OrderCreateDto;
import com.example.shop.DTO.Oder.OrderDto;
import com.example.shop.DTO.Oder.OrderUpdateDto;
import com.example.shop.DTO.Security.UserPrincipalData;
import com.example.shop.Entity.Order;
import com.example.shop.Entity.Status;
import com.example.shop.Exception.NoAccessOperation;
import com.example.shop.Exception.NoFoundException;
import com.example.shop.Mappers.OrderMapper;
import com.example.shop.Repository.OrderRepository;
import com.example.shop.Service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final UserPrincipalData userPrincipalData;

    @Override
    public Long createOrder(OrderCreateDto orderCreateDto) {
        log.info("Создание нового заказа");
        Order order = orderMapper.orderCreateDtoToOrder(orderCreateDto);
        order.setStatus(Status.CREATE);
        return orderRepository.save(order).getId();
    }

    @Override
    @Transactional
    public void deleteOrderById(Long id) {
        log.info("Удаление заказа по id");
        if(!Objects.equals(id, userPrincipalData.getId())){
            throw new NoAccessOperation("Операция обновления запрещена");
        }
        orderRepository.deleteById(id);
    }

    @Override
    public OrderDto getOrderById(Long id) {
        log.info("Выдача заказа по id");
        return orderMapper.orderToOrderDto(orderRepository.findById(id).orElseThrow(() -> {throw new NoFoundException("Заказ не нйден");}));
    }

    @Override
    public List<OrderDto> getOrdersByUserId(Long id) {
        log.info("Выдача заказа по пользователю");
        return orderRepository.getOrdersByUserIdOrderByCreateOrder(id).stream().map(orderMapper::orderToOrderDto).toList();
    }

    @Override
    public OrderDto updateOrder(OrderUpdateDto orderUpdateDto) {
        log.info("Изменение заказа");
        Order order = orderRepository.findById(orderUpdateDto.getId()).orElseThrow(() -> {throw new NoFoundException("Заказ не нйден");});
        if(orderUpdateDto.getStatus() != null){
            order.setStatus(orderUpdateDto.getStatus());
        }
        return orderMapper.orderToOrderDto(orderRepository.save(order));
    }
}
