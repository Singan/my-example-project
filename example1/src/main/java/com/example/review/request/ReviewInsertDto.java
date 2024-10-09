package com.example.review.request;

import com.example.product.ProductEntity;
import com.example.review.ReviewEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.web.multipart.MultipartFile;

public record ReviewInsertDto(
        Long userId,
        @Min(1)
        @Max(7)
        Float score,
        String content , MultipartFile image) {


    public ReviewEntity getReviewWithProduct() {


        return ReviewEntity.builder()
                .userId(userId).content(content).score(score).
                build();
    }
}
