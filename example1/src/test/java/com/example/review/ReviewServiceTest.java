package com.example.review;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.product.ProductService;
import com.example.review.repository.ReviewJpaRepository;
import com.example.product.ProductEntity;
import com.example.review.ReviewEntity;
class ReviewServiceTest {

    @Mock
    private ReviewJpaRepository reviewRepository;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ReviewService reviewService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("리뷰 추가 시 저장 및 상품 점수 업데이트")
    public void testReviewInsert() {
        // given
        Long productId = 1L;
        ReviewEntity reviewEntity = mock(ReviewEntity.class);
        ProductEntity productEntity = mock(ProductEntity.class);

        when(reviewEntity.getProductEntity()).thenReturn(productEntity);
        when(productEntity.getId()).thenReturn(productId);
        when(reviewEntity.getScore()).thenReturn(4.5f);

        // when
        reviewService.reviewInsert(reviewEntity);

        // then
        verify(reviewRepository, times(1)).save(reviewEntity);
        verify(productService, times(1)).updateProductScore(productId, 4.5f);
    }

    @Test
    @DisplayName("존재하지 않는 상품일 때 리뷰 추가 실패")
    public void testExistsProductAndUser_ProductNotExist() {
        // given
        Long productId = 1L;
        ReviewEntity reviewEntity = mock(ReviewEntity.class);
        ProductEntity productEntity = mock(ProductEntity.class);

        when(reviewEntity.getProductEntity()).thenReturn(productEntity);
        when(productEntity.getId()).thenReturn(productId);
        when(productService.productExists(productId)).thenReturn(false);

        // when
        boolean result = reviewService.existsProductAndUser(reviewEntity);

        // then
        assertFalse(result); //
    }

    @Test
    @DisplayName("상품이 존재하지만, 이미 리뷰를 작성한 유저가 있을 때 실패")
    public void testExistsProductAndUser_Fail() {
        // given
        Long userId = 1L;
        Long productId = 1L;
        ReviewEntity reviewEntity = mock(ReviewEntity.class);
        ProductEntity productEntity = mock(ProductEntity.class);

        when(reviewEntity.getProductEntity()).thenReturn(productEntity);
        when(reviewEntity.getUserId()).thenReturn(userId);
        when(productEntity.getId()).thenReturn(productId);
        when(productService.productExists(productId)).thenReturn(true);
        when(reviewRepository.existsByUserIdAndProductEntity(userId, productEntity)).thenReturn(true);

        // when
        boolean result = reviewService.existsProductAndUser(reviewEntity);

        // then
        assertFalse(result);
    }

    @Test
    @DisplayName("상품 존재하고, 중복 리뷰가 없을 때 성공")
    public void testExistsProductAndUser_Success() {
        // given
        Long userId = 1L;
        Long productId = 1L;
        ReviewEntity reviewEntity = mock(ReviewEntity.class);
        ProductEntity productEntity = mock(ProductEntity.class);

        when(reviewEntity.getProductEntity()).thenReturn(productEntity);
        when(reviewEntity.getUserId()).thenReturn(userId);
        when(productEntity.getId()).thenReturn(productId);
        when(productService.productExists(productId)).thenReturn(true);
        when(reviewRepository.existsByUserIdAndProductEntity(userId, productEntity)).thenReturn(false);

        // when
        boolean result = reviewService.existsProductAndUser(reviewEntity);

        // then
        assertTrue(result);
    }
}
