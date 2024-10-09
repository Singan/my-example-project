package com.example.review;

import com.example.common.BaseTimeEntity;
import com.example.product.ProductEntity;
import com.example.review.domain.Review;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "review")
public class ReviewEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(20)")
    private Long id;

    @Column(nullable = false, columnDefinition = "BIGINT(20)", unique = true)
    private Long userId;

    @Column(nullable = false)
    private Float score;

    @Column
    private String imageUrl;

    @Column
    private String content;

    @JoinColumn(name = "product_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private ProductEntity productEntity;

    @Builder
    public ReviewEntity(Long id, Long userId, Float score, String imageUrl, String content, ProductEntity productEntity) {
        this.id = id;
        this.userId = userId;
        this.score = score;
        this.imageUrl = imageUrl;
        this.content = content;
        this.productEntity = productEntity;
    }


    public Review toDomain(){
        return Review.builder()
                .id(id)
                .createDateTime(getCreateDateTime())
                .content(content)
                .imageUrl(imageUrl)
                .score(score)
                .userId(userId)
                .build();
    }
}
