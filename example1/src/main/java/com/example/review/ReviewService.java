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

    public void reviewInsert(Long productId , ReviewEntity reviewEntity) {

        if (!productService.productExists(productId)) // 해당 글이 없다면 에러
            throw new IllegalArgumentException();

        if (userIdExist(reviewEntity.getUserId())) // 중복 유저가 있다면 에러
            throw new IllegalArgumentException();

        reviewRepository.save(reviewEntity);


    }

    private boolean userIdExist(Long userId) {
        return reviewRepository.existsByUserId(userId);
    }


}
