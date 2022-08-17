package com.example.shop.Service;

import com.example.shop.Entity.Product;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService{

    @Transactional
    public List<Product> getListByPredicate(List<String> predicates);

    @Transactional
    public boolean deleteProduct(Product product);

    @Transactional
    public boolean addComment(Long id, String comment);

    @Transactional
    public boolean addLikes(Long id);

    @Transactional
    public Product getProductById(Long id);

    @Transactional
    public boolean save(Product product);
}
