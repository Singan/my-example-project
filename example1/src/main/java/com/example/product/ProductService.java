package com.example.product;

import com.example.product.domain.Product;
import com.example.product.repository.ProductRepository;
import com.example.product.response.ProductDetailDto;
import com.example.product.response.ProductWithReview;
import com.example.product.util.ProductFactory;
import com.example.review.ReviewEntity;
import com.example.review.ReviewService;
import com.example.review.domain.Review;
import com.example.review.repository.ReviewJpaRepository;
import com.example.review.repository.ReviewRepository;
import com.example.review.response.ReviewResponse;
import com.example.review.util.ReviewFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    public boolean productExists(Long id) {
        return productRepository.existsById(id);
    }


    public ProductWithReview productDetail(Long id, Long cursor, PageRequest pageRequest) {
        Product product = productRepository.
                findByIdWithReviewsOrderByDescCreateDate(id);

        Slice<Review> reviews = reviewRepository.findByProductIdReviewsWithCursor(id, cursor, pageRequest);
        ProductDetailDto pd = ProductFactory.ofProductDetail(product);
        List<ReviewResponse> reviewResponseList = reviews.getContent().stream()
                .map(ReviewFactory::ofReviewResponse)
                .toList();

        // 마지막 리뷰의 id를 cursor로 사용합니다.
        Long nextCursor = !reviewResponseList.isEmpty()
                ? reviewResponseList.getLast().id()
                : null;
        return ProductFactory.ofProductWithReview(pd, nextCursor ,ReviewFactory.ofReviewPagingList(reviewResponseList) );
    }


    public Long productSave() {
        ProductEntity productEntity = ProductEntity.builder().reviewCount(0L).score(0f).build();
        return productRepository.save(productEntity);
    }

    public void updateProductScore(long id, float score) {
        productRepository.updateProductScore(id, score);
    }
}
