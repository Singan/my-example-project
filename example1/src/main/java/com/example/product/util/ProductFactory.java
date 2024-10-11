package com.example.product.util;

import com.example.product.domain.Product;
import com.example.product.response.ProductDetailDto;
import com.example.product.response.ProductWithReview;
import com.example.review.response.ReviewPagingList;
import com.example.review.util.ReviewFactory;

public class ProductFactory {


    public static ProductDetailDto ofProductDetail(Product product) {

        return new ProductDetailDto(
                product.getReviewCount(),
                product.getScore());
    }

    public static ProductWithReview ofProductWithReview(ProductDetailDto pd, Long cursor, ReviewPagingList reviewList
    ) {
        return new ProductWithReview(pd, cursor, reviewList);
    }
}
