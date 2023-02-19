package com.example.shop.Service;

import com.example.shop.DTO.Product.ProductCreateDto;
import com.example.shop.DTO.Product.ProductDTO;
import com.example.shop.DTO.Product.ProductUpdateDto;
import com.example.shop.Entity.TypeOfFood;

import java.util.List;

public interface ProductService{


    List<ProductDTO> getProductByTypeOfFood(Long typeOfFoodId);


    void deleteProductById(Long id);


    ProductDTO getProductById(Long id);


    Long save(ProductCreateDto product);


    ProductDTO updateProduct(ProductUpdateDto productUpdateDto);
}
