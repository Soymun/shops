package com.example.shop.Controllers;


import com.example.shop.DTO.Oder.OrderCreateDto;
import com.example.shop.DTO.Oder.OrderUpdateDto;
import com.example.shop.Facade.OrderFacade;
import com.example.shop.Response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderFacade orderService;

    @PostMapping("/order")
    @PreAuthorize("hasAuthority('SELL')")
    public ResponseEntity<?> createOrder(@RequestBody OrderCreateDto orderCreateDto){
        orderService.createOrder(orderCreateDto);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/order/{id}")
    @PreAuthorize("hasAuthority('SELL')")
    public ResponseEntity<?> deleteOrderById(@PathVariable Long id){
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id){
        return ResponseEntity.ok(ResponseDto.builder().data(orderService.getOrderById(id)).build());
    }

    @GetMapping("user/order/{id}")
    public ResponseEntity<?> getOrderByUserId(@PathVariable Long id){
        return ResponseEntity.ok(ResponseDto.builder().data(orderService.getOrdersByUserId(id)).build());
    }

    @PatchMapping("/order")
    @PreAuthorize("hasAuthority('SELL')")
    public ResponseEntity<?> updateOrder(@RequestBody OrderUpdateDto orderUpdateDto){
        return ResponseEntity.ok(ResponseDto.builder().data(orderService.updateOrder(orderUpdateDto)).build());
    }
}
