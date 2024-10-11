package com.example.product.repository;

import com.example.product.ProductEntity;
import com.example.product.domain.Product;
import com.example.review.ReviewEntity;
import com.example.review.repository.ReviewJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Repository

public class ProductRepositoryImpl implements ProductRepository {
    private final ProductJpaRepository productJpaRepository;


    @Override
    public boolean existsById(Long id) {
        return productJpaRepository.existsById(id);
    }

    @Override
    public Product findByIdWithReviewsOrderByDescCreateDate(Long id) {
        ProductEntity product = productJpaRepository.findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("없는 상품입니다.")
                );
        return product.toDomain();
    }

    @Override
    @Transactional
    public long save(ProductEntity productEntity) {
        return productJpaRepository.save(productEntity).getId();
    }

    @Override
    @Transactional
    public void updateProductScore(long id, float score) {
//        ProductEntity product = productJpaRepository.findByIdLock(id);
//        product.updateCountAndScore(score);

        productJpaRepository.updateProductEntityById(id, score);
    }
}
