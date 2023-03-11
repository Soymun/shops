package com.example.shop.Service.Imp;

import com.example.shop.DTO.Shop.ShopCreateDto;
import com.example.shop.DTO.Shop.ShopDto;
import com.example.shop.DTO.Shop.ShopUpdateDto;
import com.example.shop.Entity.Shop;
import com.example.shop.Exception.NoFoundException;
import com.example.shop.Mappers.ShopMapper;
import com.example.shop.Repository.ShopRepository;
import com.example.shop.Service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;

    private final ShopMapper shopMapper;

    @Override
    public void createShop(ShopCreateDto shopCreateDto) {
        log.info("Сохранение магазина");
        shopRepository.save(shopMapper.shopCreateDtoToShop(shopCreateDto));
    }

    @Override
    public List<ShopDto> getShopByCity(String city) {
        log.info("Выдача магазинов по городу {}", city);
        return shopRepository.getShopsByCity(city).stream().map(shopMapper::shopToShopDto).toList();
    }

    @Override
    public List<ShopDto> getAllShop() {
        log.info("Выдача всех магазинов");
        return shopRepository.findAll().stream().map(shopMapper::shopToShopDto).toList();
    }

    @Override
    public ShopDto updateShop(ShopUpdateDto shopUpdateDto) {
        log.info("Изменение магазина по id {}", shopUpdateDto.getId());
        Shop shop = shopRepository.findById(shopUpdateDto.getId()).orElseThrow(() -> {
            throw new NoFoundException("Магазин не был найден");
        });
        if (shopUpdateDto.getCity() != null) {
            shop.setCity(shopUpdateDto.getCity());
        }
        if (shopUpdateDto.getStreet() != null) {
            shop.setStreet(shopUpdateDto.getStreet());
        }
        if (shopUpdateDto.getHouse() != null) {
            shop.setHouse(shopUpdateDto.getHouse());
        }
        if (shopUpdateDto.getNumberHouse() != null) {
            shop.setNumberHouse(shopUpdateDto.getNumberHouse());
        }
        if (shopUpdateDto.getCordX() != null) {
            shop.setCordX(shopUpdateDto.getCordX());
        }
        if (shopUpdateDto.getCordY() != null) {
            shop.setCordY(shopUpdateDto.getCordY());
        }
        if (shopUpdateDto.getOpen() != null) {
            shop.setOpen(shopUpdateDto.getOpen());
        }
        if (shopUpdateDto.getClose() != null) {
            shop.setClose(shopUpdateDto.getClose());
        }
        if (shopUpdateDto.getPhone() != null) {
            shop.setPhone(shopUpdateDto.getPhone());
        }
        return shopMapper.shopToShopDto(shopRepository.save(shop));
    }

    @Override
    public void deleteShop(Long id) {
        log.info("Удаление магазина с id {}", id);
        shopRepository.deleteById(id);
    }

    @Override
    public ShopDto getShopById(Long id) {
        log.info("Выдача магазина по id {}", id);
        return shopMapper.shopToShopDto(shopRepository.findById(id).orElseThrow(() -> {
            throw new NoFoundException("Магазин не был найден");
        }));
    }
}
