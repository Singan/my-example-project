package com.example.review.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class Review {

    private Long id;

    private Long userId;

    private Float score;

    private String imageUrl;

    private String content;

    private LocalDateTime createDateTime;

}
