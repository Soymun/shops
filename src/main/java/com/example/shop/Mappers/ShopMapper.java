package com.example.shop.Mappers;

import com.example.shop.DTO.Shop.ShopCreateDto;
import com.example.shop.DTO.Shop.ShopDto;
import com.example.shop.Entity.Shop;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShopMapper {

    ShopDto shopToShopDto(Shop shop);

    Shop shopCreateDtoToShop(ShopCreateDto shopCreateDto);
}
