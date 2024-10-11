package com.example.review.repository;

import com.example.review.ReviewEntity;
import com.example.review.domain.Review;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository

public interface ReviewRepository {
    Slice<Review> findByProductIdReviewsWithCursor(Long id, Long cursor, PageRequest pageRequest);
}
