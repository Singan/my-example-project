package com.example.product;

import com.example.product.domain.Product;
import com.example.review.ReviewEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(20)")
    private Long id;

    //CREATE TABLE IF NOT EXISTS `Product` (
    //    `id`          BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    //    `reviewCount` BIGINT(20) NOT NULL,
    //    `score`       FLOAT  NOT NULL
    //) ENGINE = InnoDB CHARSET = utf8;
    @Column(nullable = false, columnDefinition = "BIGINT(20)")
    @ColumnDefault("0") //default 0
    private Long reviewCount;

    @Column(nullable = false)
    @ColumnDefault("0") //default 0
    private Float score;


    @Builder
    public ProductEntity(Long id, Long reviewCount, Float score) {
        this.id = id;
        this.reviewCount = reviewCount;
        this.score = score;
    }

    public void updateCountAndScore(Float score){
        reviewCount++;
        float total = this.score * reviewCount;
        total+= score;
        this.score = total/reviewCount;
    }


    public Product toDomain() {
        return Product.builder()
                .id(id)
                .reviewCount(reviewCount)
                .score(score)
                .build();
    }

}
