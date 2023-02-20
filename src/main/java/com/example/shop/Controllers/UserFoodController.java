package com.example.shop.Controllers;

import com.example.shop.DTO.UserFood.UserFoodCreateDto;
import com.example.shop.DTO.UserFood.UserFoodUpdateDto;
import com.example.shop.Facade.UserFoodFacade;
import com.example.shop.Response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserFoodController {

    private final UserFoodFacade userFoodFacade;
    @PostMapping("/user/food")
    @PreAuthorize("hasAuthority('SELL')")
    public ResponseEntity<?> createUserFood(@RequestBody UserFoodCreateDto userFoodCreateDto){
        userFoodFacade.createUserFood(userFoodCreateDto);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/user/food/{id}")
    @PreAuthorize("hasAuthority('SELL')")
    public ResponseEntity<?> deleteUserFood(@PathVariable Long id){
        userFoodFacade.deleteUserFoodById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/food/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(ResponseDto.builder().data(userFoodFacade.getUserFoodById(id)).build());
    }

    @PatchMapping("/user/food")
    @PreAuthorize("hasAuthority('SELL')")
    public ResponseEntity<?> updateFood(@RequestBody UserFoodUpdateDto userFoodUpdateDto){
        return ResponseEntity.ok(ResponseDto.builder().data(userFoodFacade.updateUserFood(userFoodUpdateDto)).build());
    }

    @GetMapping("/user/{id}/food")
    public ResponseEntity<?> getUserFoodByUserId(@PathVariable Long id){
        return ResponseEntity.ok(ResponseDto.builder().data(userFoodFacade.getUserFoodByUserId(id)).build());
    }

    @GetMapping("/order/user/food/{id}")
    public ResponseEntity<?> getUserFoodByOrderId(@PathVariable Long id){
        return ResponseEntity.ok(ResponseDto.builder().data(userFoodFacade.getUserFoodByOrderId(id)).build());
    }
}
