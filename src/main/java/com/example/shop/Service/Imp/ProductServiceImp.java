package com.example.shop.Service.Imp;

import com.example.shop.DTO.Product.ProductCreateDto;
import com.example.shop.DTO.Product.ProductDTO;
import com.example.shop.DTO.Product.ProductUpdateDto;
import com.example.shop.Entity.TypeOfFood;
import com.example.shop.Service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    @Override
    public List<ProductDTO> getProductByTypeOfFood(TypeOfFood typeOfFood) {
        return null;
    }

    @Override
    public void deleteProductById(Long id) {

    }

    @Override
    public ProductDTO getProductById(Long id) {
        return null;
    }

    @Override
    public void save(ProductCreateDto product) {

    }

    @Override
    public ProductDTO updateProduct(ProductUpdateDto productUpdateDto) {
        return null;
    }
}
