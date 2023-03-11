package com.example.shop.Controllers;

import com.example.shop.DTO.Shop.ShopCreateDto;
import com.example.shop.DTO.Shop.ShopUpdateDto;
import com.example.shop.Response.ResponseDto;
import com.example.shop.Service.Imp.ShopServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins="http://localhost:3000")
public class ShopController {

    private final ShopServiceImpl shopService;

    @ApiOperation(value = "Метод для создания магазина.", notes = "Могут пользоваться все.Возвращает код 201")
    @PostMapping("/shop")
    public ResponseEntity<?> createOrder(@RequestBody ShopCreateDto shopCreateDto){
        shopService.createShop(shopCreateDto);
        return ResponseEntity.status(201).build();
    }

    @ApiOperation(value = "Метод для удаления магазина.")
    @DeleteMapping("/shop/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteShopById(@PathVariable Long id){
        shopService.deleteShop(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Метод для получения магазина по id.")
    @GetMapping("/shop/{id}")
    public ResponseEntity<?> getShopById(@PathVariable Long id){
        return ResponseEntity.ok(ResponseDto.builder().data(shopService.getShopById(id)).build());
    }

    @ApiOperation(value = "Метод для изменения магазина", notes = "Могут пользоваться продовцы.Возвращает OrderDto")
    @PatchMapping("/shop")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateOrder(@RequestBody ShopUpdateDto shopUpdateDto){
        return ResponseEntity.ok(ResponseDto.builder().data(shopService.updateShop(shopUpdateDto)).build());
    }

    @ApiOperation(value = "Метод для получения всех магазинов.")
    @GetMapping("/shop")
    public ResponseEntity<?> getShopById(){
        return ResponseEntity.ok(ResponseDto.builder().data(shopService.getAllShop()).build());
    }

    @ApiOperation(value = "Метод для получения всех магазинов в данном городе.")
    @GetMapping("/shop/city")
    public ResponseEntity<?> getShopById(@RequestParam String city){
        return ResponseEntity.ok(ResponseDto.builder().data(shopService.getShopByCity(city)).build());
    }
}
