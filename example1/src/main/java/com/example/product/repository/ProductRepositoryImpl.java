package com.example.product.repository;

import com.example.product.ProductEntity;
import com.example.product.domain.Product;
import com.example.review.ReviewEntity;
import com.example.review.repository.ReviewJpaRepository;
import io.lettuce.core.Limit;
import jakarta.persistence.EntityManager;
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

    private final EntityManager em;


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
        // 역순으로 조회하면 리뷰가 없을 때 상품이 검색되지 않을 수 있어 분기처리를 해야한다.
        List<ReviewEntity> reviewEntities = reviewJpaRepository.findByProductEntityId(id, cursor, pageRequest);

        return product.toDomain(reviewEntities);
    }

    @Override
    public void save(ProductEntity productEntity) {
        productJpaRepository.save(productEntity);
    }


}
