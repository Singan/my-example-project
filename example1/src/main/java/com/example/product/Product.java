package com.example.product;

import com.example.review.Review;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(20)")
    private Long id;

    //CREATE TABLE IF NOT EXISTS `Product` (
    //    `id`          BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    //    `reviewCount` BIGINT(20) NOT NULL,
    //    `score`       FLOAT  NOT NULL
    //) ENGINE = InnoDB CHARSET = utf8;
    @Column(nullable = false,columnDefinition = "BIGINT(20)")
    private Long reviewCount;

    @Column(nullable = false)
    private Float score;


    @OneToMany(mappedBy = "product")
    private List<Review> reviews;



    @Builder

    public Product(Long id, Long reviewCount, Float score, List<Review> reviews) {
        this.id = id;
        this.reviewCount = reviewCount;
        this.score = score;
        this.reviews = reviews;
    }
}
