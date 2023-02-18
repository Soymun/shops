package com.example.shop.Controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class BasketController {

    @GetMapping("/getBasket/{id}")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> getBasket(@PathVariable Long id){
        return null;
    }

    @PostMapping("/addInBasket")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> addProductBasket(){
        return null;
    }

    @DeleteMapping("/deleteBasket")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> deleteBasket(){
        return null;
    }
}
