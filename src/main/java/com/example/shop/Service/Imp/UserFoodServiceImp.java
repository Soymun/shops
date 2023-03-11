package com.example.shop.Service.Imp;

import com.example.shop.DTO.Security.UserPrincipalData;
import com.example.shop.DTO.UserFood.UserFoodCreateDto;
import com.example.shop.DTO.UserFood.UserFoodDto;
import com.example.shop.DTO.UserFood.UserFoodUpdateDto;
import com.example.shop.Entity.UserProduct;
import com.example.shop.Exception.NoAccessOperation;
import com.example.shop.Exception.NoFoundException;
import com.example.shop.Mappers.UserFoodMapper;
import com.example.shop.Repository.UserFoodRepository;
import com.example.shop.Service.UserFoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserFoodServiceImp implements UserFoodService {

    private final UserFoodRepository userFoodRepository;

    private final UserFoodMapper userFoodMapper;

    private final UserPrincipalData userPrincipalData;
    @Override
    @Transactional
    public void createUserFood(UserFoodCreateDto userFoodCreateDto) {
        log.info("Сохранение пользователем продукта");
        UserProduct userProduct = userFoodMapper.userFoodCreateDtoToUserProduct(userFoodCreateDto);
        userProduct.setVisible(true);
        userFoodRepository.save(userProduct);
    }

    @Override
    @Transactional
    public void deleteUserFoodById(Long id) {
        log.info("Удаление пользователем продукта");
        if(!Objects.equals(id, userPrincipalData.getId())){
            throw new NoAccessOperation("Операция обновления запрещена");
        }
        userFoodRepository.deleteById(id);
    }

    @Override
    public UserFoodDto getUserFoodById(Long id) {
        log.info("Выдача еды пользователя по id");
        return userFoodMapper.userProductToUserFoodDto(userFoodRepository.findById(id).orElseThrow(() -> {
            throw new NoFoundException("Продукт пользователя не был найден");
        }));
    }

    @Override
    @Transactional
    public UserFoodDto updateUserFood(UserFoodUpdateDto userFoodUpdateDto) {
        log.info("Изменение еды пользователя");
        UserProduct userProduct = userFoodRepository.findById(userFoodUpdateDto.getId()).orElseThrow(() -> {
            throw new NoFoundException("Продукт пользователя не найден");
        });
        if(!Objects.equals(userProduct.getUserId(), userPrincipalData.getId())){
            throw new NoAccessOperation("Операция обновления запрещена");
        }
        if (userFoodUpdateDto.getCount() != null) {
            userProduct.setCount(userFoodUpdateDto.getCount());
        }
        if (userFoodUpdateDto.getVisible() != null) {
            userProduct.setVisible(userFoodUpdateDto.getVisible());
        }
        return userFoodMapper.userProductToUserFoodDto(userFoodRepository.save(userProduct));
    }

    @Override
    public List<UserFoodDto> getUserFoodByUserId(Long id) {
        log.info("Выдача еды пользователя по пользевателю с id");
        return userFoodRepository
                .getUserProductsByUserIdAndVisibleIsTrue(id)
                .stream()
                .map(userFoodMapper::userProductToUserFoodDto).toList();
    }

    @Override
    public List<UserFoodDto> getUserFoodByOrderId(Long id) {
        log.info("Выдача еды пользователя по order id");
        return userFoodRepository
                .getUserProductsByOrderId(id)
                .stream()
                .map(userFoodMapper::userProductToUserFoodDto).toList();
    }

    @Override
    @Transactional
    public void setAllUserFoodUnVisible(Long userId, Long orderId) {
        log.info("Изменение видимости продуктов");
        List<UserProduct> userProducts = userFoodRepository
                .getUserProductsByUserIdAndVisibleIsTrue(userId)
                .stream().peek(n -> n.setVisible(false))
                .peek(n -> n.setOrderId(orderId))
                .toList();
        userFoodRepository.saveAll(userProducts);
    }
}
