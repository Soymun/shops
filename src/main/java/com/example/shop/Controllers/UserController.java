package com.example.shop.Controllers;

import com.example.shop.DTO.Security.RoleDto;
import com.example.shop.DTO.User.UserUpdateDto;
import com.example.shop.Response.ResponseDto;
import com.example.shop.Service.Imp.UserServiceImp;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins="http://localhost:3000")
public class UserController {

    private final UserServiceImp userServiceImp;

    @ApiOperation(value = "Метод для получения пользователя по id.", notes = "Могут пользоваться всем.Возвращает UserDto")
    @GetMapping("/user/{id}")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(ResponseDto.builder().data(userServiceImp.getUserById(id)).build());
    }

    @ApiOperation(value = "Метод для изменения пользователя по id.", notes = "Могут пользоваться всем.При изменении email или password нужно перелогинится.Возвращает UserDto")
    @PatchMapping("/user")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateDto userUpdateDto){
        if(userUpdateDto.getRole() != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(ResponseDto.builder().data(userServiceImp.updateUser(userUpdateDto)).build());
    }

    @ApiOperation(value = "Метод для удаления пользователя по id.", notes = "Могут пользоваться всем.Возвращает код 204")
    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        userServiceImp.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Метод для изменения роли у пользователя по id.", notes = "Могут пользоваться admin.Возвращает UserDto")
    @PatchMapping("/user/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> regSelman(@PathVariable Long id, @RequestBody RoleDto role){
        UserUpdateDto userUpdateDto = new UserUpdateDto();
        userUpdateDto.setId(id);
        userUpdateDto.setRole(role.getRole());
        return ResponseEntity.ok(ResponseDto.builder().data(userServiceImp.updateUser(userUpdateDto)).build());
    }
}
