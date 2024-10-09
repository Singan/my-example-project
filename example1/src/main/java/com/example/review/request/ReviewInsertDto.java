package com.example.review.request;

import com.example.product.Product;
import com.example.review.Review;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record ReviewInsertDto(
        Long userId,
        @Min(1)
        @Max(7)
        Float score,
        String content) {


    public Review getReviewWithProduct(Long productId) {

        Product product = Product.builder().id(productId).build();
        return Review.builder().product(product)
                .userId(userId).content(content).score(score).
                build();
    }
}
