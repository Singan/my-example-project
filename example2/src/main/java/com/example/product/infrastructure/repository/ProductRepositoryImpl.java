package com.example.product.infrastructure.repository;

import com.example.product.domain.Product;
import com.example.product.infrastructure.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;

    @Override
    @Transactional

    public void productRestock(long productId, int stock) {
        ProductEntity productEntity = jpaProductRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("없는 상품입니다."));
        productEntity.increaseStock(stock);
    }

    @Override
    public void save(Product product){
        jpaProductRepository.save(ProductEntity.from(product));
    }

    @Override
    public Product findById(long id) {
        return jpaProductRepository
                .findById(id).map(ProductEntity::toDomain)
                .orElseThrow(() -> new NoSuchElementException("없는 상품입니다."));
    }
    @Override
    @Transactional

    public void productDecrease(long id, int decrease) {
        jpaProductRepository.findById(id).orElseThrow(() -> new NoSuchElementException("없는 상품입니다."))
                .decreaseStock(decrease);
    }
}
