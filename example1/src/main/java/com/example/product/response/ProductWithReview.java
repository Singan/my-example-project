package com.example.product.response;


import com.example.review.response.ReviewPagingList;

public record ProductWithReview(
        ProductDetailDto productDetailDto,
        Long cursor,
        ReviewPagingList reviewList

        ) {
}
