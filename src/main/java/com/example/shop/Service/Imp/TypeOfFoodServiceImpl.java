package com.example.shop.Service.Imp;

import com.example.shop.DTO.TypeOfFood.TypeOfFoodCreateDto;
import com.example.shop.DTO.TypeOfFood.TypeOfFoodDto;
import com.example.shop.DTO.TypeOfFood.TypeOfFoodUpdateDto;
import com.example.shop.Entity.TypeOfFood;
import com.example.shop.Mappers.TypeOfFoodMapper;
import com.example.shop.Repository.TypeOfFoodRepository;
import com.example.shop.Service.TypeOfFoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TypeOfFoodServiceImpl implements TypeOfFoodService {

    private final TypeOfFoodMapper typeOfFoodMapper;
    private final TypeOfFoodRepository typeOfFoodRepository;

    @Override
    public Long createTypeOfFood(TypeOfFoodCreateDto typeOfFoodCreateDto) {
        log.info("Сохранение типа продукта");
        return typeOfFoodRepository.save(typeOfFoodMapper.typeOfFoodCreateDtoToTypeOfFood(typeOfFoodCreateDto)).getId();
    }

    @Override
    public TypeOfFoodDto getTypeOfFoodById(Long id) {
        log.info("Выдача типа продукта по id {}", id);
        return typeOfFoodMapper.typeOfFoodToTypeOfFoodCreateDto(typeOfFoodRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Тип продукта нен айден");
        }));
    }

    @Override
    public TypeOfFoodDto updateTypeOfFood(TypeOfFoodUpdateDto typeOfFoodUpdateDto) {
        log.info("Изменение типа еды");
        TypeOfFood typeOfFood = typeOfFoodRepository.findById(typeOfFoodUpdateDto.getId()).orElseThrow(() -> {
            throw new RuntimeException("Тип продукта нен айден");
        });
        if(typeOfFoodUpdateDto.getName() != null){
            typeOfFood.setName(typeOfFoodUpdateDto.getName());
        }
        if(typeOfFoodUpdateDto.getUrlToPhoto() != null){
            typeOfFood.setUrlToPhoto(typeOfFoodUpdateDto.getUrlToPhoto());
        }
        if(typeOfFoodUpdateDto.getTypeOfFoodId() != null){
            typeOfFood.setTypeOfFoodId(typeOfFoodUpdateDto.getTypeOfFoodId());
        }
        return typeOfFoodMapper.typeOfFoodToTypeOfFoodCreateDto(typeOfFoodRepository.save(typeOfFood));
    }

    @Override
    @Transactional
    public void deleteTypeOfFoodById(Long id) {
        log.info("Удаление вида продукта");
        typeOfFoodRepository.deleteById(id);
    }

    @Override
    public List<TypeOfFoodDto> getSubTypeOfFoodByRootIdTypeOfFood(Long rootId) {
        log.info("Выдача подтипов еды");
        return typeOfFoodRepository.getTypeOfFoodsByTypeOfFoodId(rootId).stream().map(typeOfFoodMapper::typeOfFoodToTypeOfFoodCreateDto).toList();
    }

    @Override
    public List<TypeOfFoodDto> getRootTypeOfFood() {
        log.info("Выдача рутовых типов еды");
        return typeOfFoodRepository.getTypeOfFoodsByTypeOfFoodIdIsNull().stream().map(typeOfFoodMapper::typeOfFoodToTypeOfFoodCreateDto).toList();
    }
}
