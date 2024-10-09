package com.example.product.response;

import com.example.review.response.ReviewList;

public record ProductDetailDto (
        Long totalCount, Float score , Long cursor , ReviewList reviews
){



}
