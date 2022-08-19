package com.example.shop.Controllers;

import com.example.shop.DTO.ProductBasketDTO;
import com.example.shop.Entity.Product;
import com.example.shop.Entity.User;
import com.example.shop.Service.Imp.ProductServiceImp;
import com.example.shop.Service.Imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

public class BasketController {


    private final UserServiceImp userServiceImp;

    private final ProductServiceImp productServiceImp;

    @Autowired
    public BasketController(UserServiceImp userServiceImp, ProductServiceImp productServiceImp) {
        this.userServiceImp = userServiceImp;
        this.productServiceImp = productServiceImp;
    }

    @GetMapping("/getBasket/{id}")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> getBasket(@PathVariable Long id){
        User user = userServiceImp.findUserById(id);
        return ResponseEntity.ok(user.getBasket().getProductList());
    }

    @PostMapping("/addInBasket")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> addProductBasket(@RequestBody ProductBasketDTO productDTO){
        User user = userServiceImp.findUserById(productDTO.getUserId());
        Product product = productServiceImp.getProductById(productDTO.getProductId());
        user.getBasket().getProductList().add(product);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/deleteBasket")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> deleteBasket(@RequestBody ProductBasketDTO productDTO){
        User user = userServiceImp.findUserById(productDTO.getUserId());
        Product product = productServiceImp.getProductById(productDTO.getProductId());
        user.getBasket().getProductList().remove(product);
        return ResponseEntity.ok(user);
    }
}
