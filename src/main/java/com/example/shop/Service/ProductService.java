package com.example.shop.Service;

import com.example.shop.DTO.ProductGetDTO;
import com.example.shop.DTO.ProductListDTO;
import com.example.shop.DTO.ProductSortedDTO;
import com.example.shop.Entity.Product;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService{

    @Transactional
    List<ProductGetDTO> getOrderList(ProductListDTO productListDTO);

    @Transactional
    boolean deleteProduct(Product product);

    @Transactional
    boolean addComment(Long id, String comment);

    @Transactional
    boolean addLikes(Long id);

    @Transactional
    Product getProductById(Long id);

    @Transactional
    void save(Product product);

    @Transactional
    List<ProductGetDTO> getSortedListProduct(ProductSortedDTO productSortedDTO);
}
