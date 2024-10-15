package com.example.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer stock;

    private Integer restockRound;

    @Builder
    public ProductEntity(Long id, String name, Integer stock, Integer restockRound) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.restockRound = restockRound;
    }


    public Product toDomain() {
        return Product.
                builder().
                id(id).
                name(name).
                stock(stock).
                restockRound(restockRound).
                build();
    }

    public static ProductEntity from(Product product) {
        return ProductEntity.
                builder().
                id(product.getId()).
                name(product.getName()).
                stock(product.getStock()).
                restockRound(product.getRestockRound()).
                build();
    }

    @Transactional
    public int increaseStock(int stock) {
        this.stock += stock;
        restockRound = restockRound + 1;
        return stock;
    }

    @Transactional
    public int decreaseStock(int stock) {
        this.stock -= stock;
        if (this.stock < 0)
            this.stock = 0;
        return this.stock;
    }
}
