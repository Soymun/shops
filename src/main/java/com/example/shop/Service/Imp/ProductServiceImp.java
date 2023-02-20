package com.example.shop.Service.Imp;

import com.example.shop.DTO.Product.ProductCreateDto;
import com.example.shop.DTO.Product.ProductDTO;
import com.example.shop.DTO.Product.ProductUpdateDto;
import com.example.shop.Entity.Product;
import com.example.shop.Entity.Product_;
import com.example.shop.Mappers.ProductsMapper;
import com.example.shop.Repository.ProductRepository;
import com.example.shop.Service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImp implements ProductService {

    private final ProductRepository repository;

    private final ProductsMapper productsMapper;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<ProductDTO> getProductByTypeOfFood(Long typeOfFoodId) {
        log.info("Получение всех позиций по id вида еды {}", typeOfFoodId);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductDTO> cq = cb.createQuery(ProductDTO.class);
        Root<Product> root = cq.from(Product.class);

        cq.where(cb.equal(root.get(Product_.typeOfFoodId), typeOfFoodId));

        cq.multiselect(
                root.get(Product_.id),
                root.get(Product_.name),
                root.get(Product_.about),
                root.get(Product_.weight),
                root.get(Product_.typeOfWeight),
                root.get(Product_.typeOfFoodId),
                root.get(Product_.calories),
                root.get(Product_.price),
                root.get(Product_.urlToPngFile),
                root.get(Product_.inBallsProgram),
                root.get(Product_.ballsPrice)
        );
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    @Transactional
    public void deleteProductById(Long id) {
        log.info("Удаление позиции по id {}", id);
        repository.deleteById(id);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        log.info("Выдача продукта по id {}", id);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductDTO> cq = cb.createQuery(ProductDTO.class);
        Root<Product> root = cq.from(Product.class);

        cq.where(cb.equal(root.get(Product_.id), id));

        cq.multiselect(
                root.get(Product_.id),
                root.get(Product_.name),
                root.get(Product_.about),
                root.get(Product_.weight),
                root.get(Product_.typeOfWeight),
                root.get(Product_.typeOfFoodId),
                root.get(Product_.calories),
                root.get(Product_.price),
                root.get(Product_.urlToPngFile),
                root.get(Product_.inBallsProgram),
                root.get(Product_.ballsPrice)
        );
        return entityManager.createQuery(cq).getSingleResult();
    }

    @Override
    public Long save(ProductCreateDto product) {
        log.info("Солхранение продукта {}", product.getName());
        return repository.save(productsMapper.productCreateDtoToProduct(product)).getId();
    }

    @Override
    public ProductDTO updateProduct(ProductUpdateDto productUpdateDto) {
        log.info("Изменение продукта");
        Product product = repository.findProductById(productUpdateDto.getId()).orElseThrow(() -> {
            throw new RuntimeException("Продукт не был найден");
        });
        if(productUpdateDto.getName() != null){
            product.setName(productUpdateDto.getName());
        }
        if(productUpdateDto.getAbout() != null){
            product.setAbout(productUpdateDto.getAbout());
        }
        if(productUpdateDto.getWeight() != null){
            product.setWeight(productUpdateDto.getWeight());
        }
        if(productUpdateDto.getCalories() != null){
            product.setCalories(productUpdateDto.getCalories());
        }
        if(productUpdateDto.getPrice() != null){
            product.setPrice(productUpdateDto.getPrice());
        }
        if(productUpdateDto.getInBallsProgram() != null){
            product.setInBallsProgram(productUpdateDto.getInBallsProgram());
        }
        if(productUpdateDto.getBallsPrice() != null){
            product.setBallsPrice(productUpdateDto.getBallsPrice());
        }
        return productsMapper.productToProductDto(repository.save(product));
    }
}
