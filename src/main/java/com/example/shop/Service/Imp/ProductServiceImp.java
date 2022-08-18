package com.example.shop.Service.Imp;

import com.example.shop.DTO.ProductGetDTO;
import com.example.shop.DTO.ProductListDTO;
import com.example.shop.DTO.ProductSortedDTO;
import com.example.shop.Entity.Comment;
import com.example.shop.Entity.Product;
import com.example.shop.Entity.Product_;
import com.example.shop.Repository.ProductRepository;
import com.example.shop.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

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
        Predicate[] predicates = new Predicate[3];
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductGetDTO> cq = cb.createQuery(ProductGetDTO.class);
        Root<Product> root = cq.from(Product.class);
        cq.multiselect(
                root.get(Product_.id),
                root.get(Product_.name),
                root.get(Product_.price),
                root.get(Product_.urlToPngFile),
                root.get(Product_.likes)
        );
        if(productSortedDTO.getName() != null){
            predicates[0] = (cb.like(root.get(Product_.name), "%" + productSortedDTO.getName() + "%"));
        }
        if(productSortedDTO.getFromprice() != null){
            if(productSortedDTO.getToprice() != null){
                predicates[1] = (cb.between(root.get(Product_.price),productSortedDTO.getFromprice(),  productSortedDTO.getToprice()));
            }
            else {
                predicates[1] = (cb.between(root.get(Product_.price),productSortedDTO.getFromprice(),  Long.MAX_VALUE));
            }
        }
        if(productSortedDTO.getFromlikes() != null){
            if(productSortedDTO.getTolikes() != null){
                predicates[2] = (cb.between(root.get(Product_.price),productSortedDTO.getFromlikes(),  productSortedDTO.getTolikes()));
            }
            else {
                predicates[2] = (cb.between(root.get(Product_.price),productSortedDTO.getFromlikes(),  Long.MAX_VALUE));
            }
        }
        Predicate[] predicates1 = new Predicate[(int) Arrays.stream(predicates).filter(Objects::nonNull).count()];
        AtomicInteger i = new AtomicInteger();
        Arrays.stream(predicates).filter(Objects::nonNull).forEach(n -> {
            predicates1[i.get()] = n;
            i.getAndIncrement();
        });
        cq.where(cb.and(predicates1));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<ProductGetDTO> getOrderList(ProductListDTO productListDTO) {
        List<Order> orders = new ArrayList<>();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductGetDTO> cq = cb.createQuery(ProductGetDTO.class);
        Root<Product> root = cq.from(Product.class);

        cq.multiselect(
                root.get(Product_.id),
                root.get(Product_.name),
                root.get(Product_.price),
                root.get(Product_.urlToPngFile),
                root.get(Product_.likes)
        );
        if(productListDTO.getName() != null){
            if(productListDTO.isADName()){
                orders.add(cb.asc(root.get(Product_.name)));
            }
            else {
                orders.add(cb.desc(root.get(Product_.name)));
            }
        }
        if(productListDTO.getPrice() != null){
            if(productListDTO.isADPrice()){
                orders.add(cb.asc(root.get(Product_.price)));
            }
            else {
                orders.add(cb.desc(root.get(Product_.price)));
            }
        }
        if(productListDTO.getLikes() != null){
            if(productListDTO.isADLikes()){
                orders.add(cb.asc(root.get(Product_.likes)));
            }
            else {
                orders.add(cb.desc(root.get(Product_.likes)));
            }
        }
        cq.orderBy(orders);
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
    public void save(Product product) {
        productRepository.save(product);
    }
}
