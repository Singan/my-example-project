package com.example.review.util;

import com.example.review.domain.Review;
import com.example.review.response.ReviewPagingList;
import com.example.review.response.ReviewResponse;

import java.util.List;

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

    public static ReviewPagingList ofReviewPagingList(List<ReviewResponse> list){
        return new ReviewPagingList(list);
    }
}
