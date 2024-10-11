package com.example.review;

import com.example.file.S3Service;
import com.example.product.ProductEntity;
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

        reviewRepository.save(reviewEntity);
        productService.updateProductScore(reviewEntity.getProductEntity().getId(), reviewEntity.getScore());

    }

    private boolean userIdExist(Long userId, ProductEntity productEntity) {
        return reviewRepository.existsByUserIdAndProductEntity(userId, productEntity);
    }


    public boolean existsProductAndUser(ReviewEntity reviewEntity) {

        ProductEntity productEntity = reviewEntity.getProductEntity();
        if (!productService.productExists(productEntity.getId())) // 해당 글이 없다면 에러
            return false;

        if (userIdExist(reviewEntity.getUserId(), productEntity)) // 중복 유저가 있다면 에러
            return false;

        return true;
    }



}
