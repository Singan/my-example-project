package com.example.product.util;

import com.example.product.domain.Product;
import com.example.product.response.ProductDetailDto;
import com.example.review.response.ReviewList;
import com.example.review.util.ReviewFactory;

public class ProductFactory {


    public static ProductDetailDto ofProductDetail(Product product, Long cursor) {

        return new ProductDetailDto(
                product.getReviewCount(),
                product.getScore(),
                cursor,
                new ReviewList(
                        product.getReviews()
                                .stream()
                                .map(r -> ReviewFactory.ofReviewResponse(r)).toList())
        );
    }
}
