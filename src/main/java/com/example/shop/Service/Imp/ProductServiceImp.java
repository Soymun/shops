package com.example.shop.Service.Imp;

import com.example.shop.Entity.Comment;
import com.example.shop.Entity.Product;
import com.example.shop.Repository.ProductRepository;
import com.example.shop.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> getListByPredicate(List<String> predicates) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public boolean deleteProduct(Product product) {
        productRepository.delete(product);
        return true;
    }

    @Override
    public boolean addComment(Long id, String comment) {
        Product product = productRepository.findProductById(id);
        if(product != null){
            Comment comment1 = new Comment();
            comment1.setComment(comment);
            product.getCommentList().add(comment1);
            productRepository.save(product);
            return true;

        }
        return false;
    }

    @Override
    public boolean addLikes(Long id) {
        Product product = productRepository.findProductById(id);
        product.setLikes(product.getLikes() + 1L);
        productRepository.save(product);
        return true;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findProductById(id);
    }

    @Override
    public boolean save(Product product) {
        productRepository.save(product);
        return true;
    }
}
