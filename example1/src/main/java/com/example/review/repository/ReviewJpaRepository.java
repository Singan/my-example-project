package com.example.review.repository;

import com.example.product.ProductEntity;
import com.example.review.ReviewEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewJpaRepository extends JpaRepository<ReviewEntity, Long> {

    boolean existsByUserId(Long userId);

    @Query("select r from ReviewEntity r" +
            " where r.id > :cursor and r.productEntity.id = :productId " +
            " order by r.createDateTime desc ")
    List<ReviewEntity> findByProductEntityId(@Param("productId") Long productId,
                                             @Param("cursor") Long cursor,
                                             Pageable pageable);
}
