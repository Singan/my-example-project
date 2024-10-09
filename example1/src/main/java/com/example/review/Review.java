package com.example.review;

import com.example.common.BaseTimeEntity;
import com.example.product.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(20)")
    private Long id;

    @Column(nullable = false, columnDefinition = "BIGINT(20)")
    private Long userId;

    @Column(nullable = false)
    private Float score;

    @Column
    private String imageUrl;

    @Column
    private String content;

    @JoinColumn
    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    @Builder
    public Review(Long id, Long userId, Float score, String imageUrl, String content,Product product) {
        this.id = id;
        this.userId = userId;
        this.score = score;
        this.imageUrl = imageUrl;
        this.content = content;
    }
}
