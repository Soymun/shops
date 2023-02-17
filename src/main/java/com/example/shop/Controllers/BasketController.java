package com.example.shop.Controllers;

import com.example.shop.DTO.ProductBasketDTO;
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
    public ResponseEntity<?> addProductBasket(@RequestBody ProductBasketDTO productDTO){
        return null;
    }

    @DeleteMapping("/deleteBasket")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> deleteBasket(@RequestBody ProductBasketDTO productDTO){
        return null;
    }
}
