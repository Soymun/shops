package com.example.shop.Service.Imp;

import com.example.shop.Entity.Product;
import com.example.shop.Service.ProductService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ProductServiceImp implements ProductService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> getListByPredicate(List<String> predicates) {
        return null;
    }

    @Override
    public boolean deleteProduct(Product product) {
        return false;
    }

    @Override
    public boolean addComment(Long id, String comment) {
        return false;
    }

    @Override
    public boolean addLikes(Long id) {
        return false;
    }
}
