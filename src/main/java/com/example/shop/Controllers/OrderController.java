package com.example.shop.Controllers;


import com.example.shop.DTO.Oder.OrderCreateDto;
import com.example.shop.DTO.Oder.OrderUpdateDto;
import com.example.shop.Facade.OrderFacade;
import com.example.shop.Response.ResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderFacade orderService;

    @ApiOperation(value = "Метод для создания заказа.", notes = "Могут пользоваться все.Возвращает код 201")
    @PostMapping("/order")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> createOrder(@RequestBody OrderCreateDto orderCreateDto){
        orderService.createOrder(orderCreateDto);
        return ResponseEntity.status(201).build();
    }

    @ApiOperation(value = "Метод для удаления заказа.", notes = "Могут пользоваться все.Возвращает код 204")
    @DeleteMapping("/order/{id}")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> deleteOrderById(@PathVariable Long id){
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Метод для получения заказа по id.", notes = "Могут пользоваться все.Возвращает OrderDto")
    @GetMapping("/order/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id){
        return ResponseEntity.ok(ResponseDto.builder().data(orderService.getOrderById(id)).build());
    }

    @ApiOperation(value = "Метод для получения заказов по user id.", notes = "Могут пользоваться все.Возвращает list OrderDto")
    @GetMapping("user/order/{id}")
    public ResponseEntity<?> getOrderByUserId(@PathVariable Long id){
        return ResponseEntity.ok(ResponseDto.builder().data(orderService.getOrdersByUserId(id)).build());
    }

    @ApiOperation(value = "Метод для изменения заказа", notes = "Могут пользоваться продовцы.Возвращает OrderDto")
    @PatchMapping("/order")
    @PreAuthorize("hasAuthority('SELL')")
    public ResponseEntity<?> updateOrder(@RequestBody OrderUpdateDto orderUpdateDto){
        return ResponseEntity.ok(ResponseDto.builder().data(orderService.updateOrder(orderUpdateDto)).build());
    }
}
