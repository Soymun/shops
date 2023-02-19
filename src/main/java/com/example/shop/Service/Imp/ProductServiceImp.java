package com.example.shop.Service.Imp;

import com.example.shop.DTO.Product.ProductCreateDto;
import com.example.shop.DTO.Product.ProductDTO;
import com.example.shop.DTO.Product.ProductUpdateDto;
import com.example.shop.Entity.Product;
import com.example.shop.Mappers.ProductsMapper;
import com.example.shop.Repository.ProductRepository;
import com.example.shop.Service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImp implements ProductService {

    private final ProductRepository repository;

    private final ProductsMapper productsMapper;

    @Override
    public List<ProductDTO> getProductByTypeOfFood(Long typeOfFoodId) {
        log.info("Получение всех позиций по id вида еды {}", typeOfFoodId);
        return repository
                .findProductsByTypeOfFoodIdOrderByPrice(typeOfFoodId)
                .stream()
                .map(productsMapper::productToProductDto).toList();
    }

    @Override
    @Transactional
    public void deleteProductById(Long id) {
        log.info("Удаление позиции по id {}", id);
        repository.deleteById(id);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        log.info("Выдача продукта по id {}", id);
        return productsMapper.productToProductDto(repository.findProductById(id).orElseThrow(() -> {
            throw new RuntimeException("Продукт не был найден");
        }));
    }

    @Override
    public void save(ProductCreateDto product) {
        log.info("Солхранение продукта {}", product.getName());
        repository.save(productsMapper.productCreateDtoToProduct(product));
    }

    @Override
    public ProductDTO updateProduct(ProductUpdateDto productUpdateDto) {
        log.info("Изменение продукта");
        Product product = repository.findProductById(productUpdateDto.getId()).orElseThrow(() -> {
            throw new RuntimeException("Продукт не был найден");
        });
        if(productUpdateDto.getName() != null){
            product.setName(productUpdateDto.getName());
        }
        if(productUpdateDto.getAbout() != null){
            product.setAbout(productUpdateDto.getAbout());
        }
        if(productUpdateDto.getWeight() != null){
            product.setWeight(productUpdateDto.getWeight());
        }
        if(productUpdateDto.getCalories() != null){
            product.setCalories(productUpdateDto.getCalories());
        }
        if(productUpdateDto.getPrice() != null){
            product.setPrice(productUpdateDto.getPrice());
        }
        if(productUpdateDto.getUrlToPngFile() != null){
            product.setUrlToPngFile(productUpdateDto.getUrlToPngFile());
        }
        if(productUpdateDto.getInBallsProgram() != null){
            product.setInBallsProgram(productUpdateDto.getInBallsProgram());
        }
        if(productUpdateDto.getBallsPrice() != null){
            product.setBallsPrice(productUpdateDto.getBallsPrice());
        }
        return productsMapper.productToProductDto(repository.save(product));
    }
}
