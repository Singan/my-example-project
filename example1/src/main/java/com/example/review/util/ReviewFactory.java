package com.example.review.util;

import com.example.review.domain.Review;
import com.example.review.response.ReviewResponse;

public class ReviewFactory {


    public static ReviewResponse ofReviewResponse(Review review) {

        return new ReviewResponse(
                review.getId(),
                review.getUserId(),
                review.getScore(),
                review.getImageUrl(),
                review.getContent(),
                review.getCreateDateTime());
    }
}
