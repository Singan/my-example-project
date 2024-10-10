package com.example.review.request;

import com.example.product.ProductEntity;
import com.example.review.ReviewEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.web.multipart.MultipartFile;

public record ReviewInsertDto(
        Long userId,
        @Min(1)
        @Max(5)
        Float score,
        String content,
        MultipartFile image
) {


    public ReviewEntity getReview(Long productId, String path) {
        return ReviewEntity.builder()
                .productEntity(ProductEntity.builder().id(productId).build())
                .userId(userId).content(content).score(score).imageUrl(path + image.getOriginalFilename()).
                build();
    }
    public ReviewEntity getReview(Long productId) {
        return ReviewEntity.builder()
                .productEntity(ProductEntity.builder().id(productId).build())
                .userId(userId).content(content).score(score).
                build();
    }
}
