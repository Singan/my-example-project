package com.example.review;

import com.example.product.ProductRepository;
import com.example.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductService productService;

    public void reviewInsert(Long productId, Review review) {

        if (!productService.productExists(productId))
            throw new IllegalArgumentException();

        if (!userIdExist(review.getUserId()))
            throw new IllegalArgumentException();

        reviewRepository.save(review);

    }

    private boolean userIdExist(Long userId) {
        return reviewRepository.existsByUserId(userId);
    }


}
