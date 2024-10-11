package com.example.review.repository;

import com.example.review.ReviewEntity;
import com.example.review.domain.Review;
import com.example.review.response.ReviewPagingList;
import com.example.review.response.ReviewResponse;
import com.example.review.util.ReviewFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository

public class ReviewRepositoryImpl implements ReviewRepository{

    private final ReviewJpaRepository reviewJpaRepository;

    public Slice<Review> findByProductIdReviewsWithCursor(Long id, Long cursor, PageRequest pageRequest) {
        Slice<ReviewEntity> reviewEntities = reviewJpaRepository.findByProductEntityId(id, cursor, pageRequest);
        return reviewEntities.map(ReviewEntity::toDomain);
    }

}
