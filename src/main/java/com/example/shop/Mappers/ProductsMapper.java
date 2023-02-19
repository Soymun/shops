package com.example.shop.Mappers;

import com.example.shop.DTO.Product.ProductCreateDto;
import com.example.shop.DTO.Product.ProductDTO;
import com.example.shop.DTO.Product.ProductUpdateDto;
import com.example.shop.Entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductsMapper {

    ProductDTO productToProductDto(Product product);

    Product productCreateDtoToProduct(ProductCreateDto productCreateDto);

    ProductUpdateDto productDtoToProduct(ProductDTO productDTO);
}
