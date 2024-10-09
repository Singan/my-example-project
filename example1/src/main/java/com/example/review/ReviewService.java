package com.example.review;

import com.example.product.ProductService;
import com.example.review.repository.ReviewJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewJpaRepository reviewRepository;
    private final ProductService productService;

    public void reviewInsert(ReviewEntity reviewEntity) {

        if (!productService.productExists(reviewEntity.getProductEntity().getId()))
            throw new IllegalArgumentException();

        if (!userIdExist(reviewEntity.getUserId()))
            throw new IllegalArgumentException();

        reviewRepository.save(reviewEntity);

    }

    private boolean userIdExist(Long userId) {
        return reviewRepository.existsByUserId(userId);
    }


}
