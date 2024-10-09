package com.example.review.request;

import com.example.product.ProductEntity;
import com.example.review.ReviewEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record ReviewInsertDto(
        Long userId,
        @Min(1)
        @Max(7)
        Float score,
        String content) {


    public ReviewEntity getReviewWithProduct(Long productId) {

        ProductEntity productEntity = ProductEntity.builder().id(productId).build();
        return ReviewEntity.builder().productEntity(productEntity)
                .userId(userId).content(content).score(score).
                build();
    }
}
