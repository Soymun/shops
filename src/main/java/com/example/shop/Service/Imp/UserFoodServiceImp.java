package com.example.shop.Service.Imp;

import com.example.shop.DTO.UserFood.UserFoodCreateDto;
import com.example.shop.DTO.UserFood.UserFoodDto;
import com.example.shop.DTO.UserFood.UserFoodUpdateDto;
import com.example.shop.Entity.Product;
import com.example.shop.Entity.Product_;
import com.example.shop.Entity.UserProduct;
import com.example.shop.Entity.UserProduct_;
import com.example.shop.Mappers.UserFoodMapper;
import com.example.shop.Repository.UserFoodRepository;
import com.example.shop.Service.UserFoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserFoodServiceImp implements UserFoodService {

    private final UserFoodRepository userFoodRepository;

    private final UserFoodMapper userFoodMapper;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void createUserFood(UserFoodCreateDto userFoodCreateDto) {
        log.info("Сохранение пользователем продукта");
        userFoodRepository.save(userFoodMapper.userFoodCreateDtoToUserProduct(userFoodCreateDto));
    }

    @Override
    @Transactional
    public void deleteUserFoodById(Long id) {
        log.info("Удаление пользователем продукта");
        userFoodRepository.deleteById(id);
    }

    @Override
    public UserFoodDto getUserFoodById(Long id) {
        log.info("Выдача еды пользователя по id");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserFoodDto> cq = cb.createQuery(UserFoodDto.class);
        Root<UserProduct> root = cq.from(UserProduct.class);

        Subquery<Product> subquery = cq.subquery(Product.class);
        Root<Product> subRoot = subquery.from(Product.class);

        subquery.where(cb.equal(subRoot.get(Product_.id), root.get(UserProduct_.productId)));

        subquery.select(subRoot);

        cq.where(cb.equal(root.get(UserProduct_.id), id));

        cq.multiselect(
                root.get(UserProduct_.id),
                root.get(UserProduct_.userId),
                subquery,
                root.get(UserProduct_.orderId),
                root.get(UserProduct_.count)
        );
        return entityManager.createQuery(cq).getSingleResult();
    }

    @Override
    public UserFoodDto updateUserFood(UserFoodUpdateDto userFoodUpdateDto) {
        log.info("Изменение еды пользователя");
        UserProduct userProduct = userFoodRepository.findById(userFoodUpdateDto.getId()).orElseThrow(() -> {
            throw new RuntimeException("Продукт пользователя не найден");
        });
        if (userFoodUpdateDto.getCount() != null) {
            userProduct.setCount(userProduct.getCount());
        }
        if (userFoodUpdateDto.getVisible() != null) {
            userProduct.setVisible(userFoodUpdateDto.getVisible());
        }
        return userFoodMapper.userProductToUserFoodDto(userFoodRepository.save(userProduct));
    }

    @Override
    public List<UserFoodDto> getUserFoodByUserId(Long id) {
        log.info("Выдача еды пользователя по пользевателю с id");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserFoodDto> cq = cb.createQuery(UserFoodDto.class);
        Root<UserProduct> root = cq.from(UserProduct.class);

        Subquery<Product> subquery = cq.subquery(Product.class);
        Root<Product> subRoot = subquery.from(Product.class);

        subquery.where(cb.equal(subRoot.get(Product_.id), root.get(UserProduct_.productId)));

        subquery.select(subRoot);

        cq.where(cb.and(cb.equal(root.get(UserProduct_.userId), id), cb.equal(root.get(UserProduct_.visible), true)));

        cq.multiselect(
                root.get(UserProduct_.id),
                root.get(UserProduct_.userId),
                subquery,
                root.get(UserProduct_.orderId),
                root.get(UserProduct_.count)
        );
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<UserFoodDto> getUserFoodByOrderId(Long id) {
        log.info("Выдача еды пользователя по order id");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserFoodDto> cq = cb.createQuery(UserFoodDto.class);
        Root<UserProduct> root = cq.from(UserProduct.class);

        Subquery<Product> subquery = cq.subquery(Product.class);
        Root<Product> subRoot = subquery.from(Product.class);

        subquery.where(cb.equal(subRoot.get(Product_.id), root.get(UserProduct_.productId)));

        subquery.select(subRoot);

        cq.where(cb.equal(root.get(UserProduct_.orderId), id));

        cq.multiselect(
                root.get(UserProduct_.id),
                root.get(UserProduct_.userId),
                subquery,
                root.get(UserProduct_.orderId),
                root.get(UserProduct_.count)
        );
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public void setAllUserFoodUnVisible(Long userId, Long orderId) {
        log.info("Изменение видимости продуктов");
        List<UserProduct> userProducts = userFoodRepository
                .getUserProductsByUserIdAndVisible(userId, true)
                .stream().peek(n -> n.setVisible(false))
                .peek(n -> n.setOrderId(orderId))
                .toList();
        userFoodRepository.saveAll(userProducts);
    }
}
