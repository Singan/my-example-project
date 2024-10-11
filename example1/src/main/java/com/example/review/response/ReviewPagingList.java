package com.example.review.response;

import org.springframework.data.domain.Slice;

import java.util.List;

public record ReviewPagingList(List<ReviewResponse> reviewResponseList) {
}
