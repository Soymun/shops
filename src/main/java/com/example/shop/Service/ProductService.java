package com.example.shop.Service;

import com.example.shop.DTO.Product.ProductCreateDto;
import com.example.shop.DTO.Product.ProductDTO;
import com.example.shop.DTO.Product.ProductGetDTO;
import com.example.shop.DTO.Product.ProductUpdateDto;
import com.example.shop.DTO.ProductListDTO;
import com.example.shop.DTO.ProductSortedDTO;
import com.example.shop.Entity.Product;
import com.example.shop.Entity.TypeOfFood;

import java.util.List;

public interface ProductService{


    List<ProductDTO> getProductByTypeOfFood(TypeOfFood typeOfFood);


    void deleteProductById(Long id);


    ProductDTO getProductById(Long id);


    void save(ProductCreateDto product);


    ProductDTO updateProduct(ProductUpdateDto productUpdateDto);
}
