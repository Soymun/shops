package com.example.shop.Service.Imp;

import com.example.shop.DTO.ProductGetDTO;
import com.example.shop.DTO.ProductListDTO;
import com.example.shop.DTO.ProductSortedDTO;
import com.example.shop.Entity.Product;
import com.example.shop.Repository.ProductRepository;
import com.example.shop.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProductGetDTO> getSortedListProduct(ProductSortedDTO productSortedDTO) {
        return null;
    }

    @Override
    public List<ProductGetDTO> getOrderList(ProductListDTO productListDTO) {
        return null;
    }

    @Override
    public boolean deleteProduct(Product product) {
        productRepository.delete(product);
        return true;
    }

    @Override
    public boolean addComment(Long id, String comment) {
        return true;
    }

    @Override
    public boolean addLikes(Long id) {
        return true;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findProductById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }
}
