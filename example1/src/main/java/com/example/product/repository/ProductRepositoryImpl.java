package com.example.product.repository;

import com.example.common.ReviewPaging;
import com.example.product.ProductEntity;
import com.example.product.domain.Product;
import com.example.review.ReviewEntity;
import com.example.review.repository.ReviewJpaRepository;
import io.lettuce.core.Limit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final ReviewJpaRepository reviewJpaRepository;
    private final ProductJpaRepository productJpaRepository;


    @Override
    public boolean existsById(Long id) {
        return productJpaRepository.existsById(id);
    }

    @Override
    public Product findByIdWithReviewsOrderByDescCreateDate(Long id, Long cursor, PageRequest pageRequest) {
        ProductEntity product = productJpaRepository.findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("없는 상품입니다.")
                );

        List<ReviewEntity> reviewEntities = reviewJpaRepository.findByProductEntityId(id, cursor, pageRequest);

        return product.toDomain(reviewEntities);
    }

    @Override
    public void save(ProductEntity productEntity) {
        productJpaRepository.save(productEntity);
    }


}
