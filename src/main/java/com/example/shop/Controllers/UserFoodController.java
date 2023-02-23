package com.example.shop.Controllers;

import com.example.shop.DTO.UserFood.UserFoodCreateDto;
import com.example.shop.DTO.UserFood.UserFoodUpdateDto;
import com.example.shop.Facade.UserFoodFacade;
import com.example.shop.Response.ResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins="http://localhost:3000")
public class UserFoodController {

    private final UserFoodFacade userFoodFacade;
    @ApiOperation(value = "Метод для создания еды пользователя.", notes = "Могут пользоваться все.Возвращает код 201")
    @PostMapping("/user/food")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> createUserFood(@RequestBody UserFoodCreateDto userFoodCreateDto){
        userFoodFacade.createUserFood(userFoodCreateDto);
        return ResponseEntity.status(201).build();
    }

    @ApiOperation(value = "Метод для удаления еды пользователя по id.", notes = "Могут пользоваться все.Возвращает 204")
    @DeleteMapping("/user/food/{id}")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> deleteUserFood(@PathVariable Long id){
        userFoodFacade.deleteUserFoodById(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Метод для получения еды пользователя по id.", notes = "Могут пользоваться все.Возвращает UserFoodDto")
    @GetMapping("/user/food/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(ResponseDto.builder().data(userFoodFacade.getUserFoodById(id)).build());
    }

    @ApiOperation(value = "Метод для изменения еды пользователя по id.", notes = "Могут пользоваться все.Возвращает UserDto")
    @PatchMapping("/user/food")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> updateFood(@RequestBody UserFoodUpdateDto userFoodUpdateDto){
        return ResponseEntity.ok(ResponseDto.builder().data(userFoodFacade.updateUserFood(userFoodUpdateDto)).build());
    }

    @ApiOperation(value = "Метод для получения роли еды пользователя по user id.", notes = "Могут пользоваться все.Возвращает list UserDto")
    @GetMapping("/user/{id}/food")
    public ResponseEntity<?> getUserFoodByUserId(@PathVariable Long id){
        return ResponseEntity.ok(ResponseDto.builder().data(userFoodFacade.getUserFoodByUserId(id)).build());
    }

    @ApiOperation(value = "Метод для получения еды пользователя по order id.", notes = "Могут пользоваться все.Возвращает list UserDto")
    @GetMapping("/order/user/food/{id}")
    public ResponseEntity<?> getUserFoodByOrderId(@PathVariable Long id){
        return ResponseEntity.ok(ResponseDto.builder().data(userFoodFacade.getUserFoodByOrderId(id)).build());
    }
}
