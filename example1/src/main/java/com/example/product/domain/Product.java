package com.example.product.domain;

import com.example.product.response.ProductDetailDto;
import com.example.review.ReviewEntity;
import com.example.review.domain.Review;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Builder
@Getter
public class Product {

    private Long id;

    private Long reviewCount;

    private Float score;


}
