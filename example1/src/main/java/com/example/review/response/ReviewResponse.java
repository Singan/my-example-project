package com.example.review.response;

import com.example.review.domain.Review;
import lombok.Getter;

import java.time.LocalDateTime;

public record ReviewResponse(Long id , Long userId, Float score , String imageUrl , String content , LocalDateTime createDateTime) {
}
