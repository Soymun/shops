package com.example.shop.Service;

import com.example.shop.DTO.Shop.ShopCreateDto;
import com.example.shop.DTO.Shop.ShopDto;
import com.example.shop.DTO.Shop.ShopUpdateDto;

import java.util.List;

public interface ShopService {

    void createShop(ShopCreateDto shopCreateDto);

    List<ShopDto> getShopByCity(String city);

    List<ShopDto> getAllShop();

    ShopDto updateShop(ShopUpdateDto shopUpdateDto);

    void deleteShop(Long id);

    ShopDto getShopById(Long id);
}
