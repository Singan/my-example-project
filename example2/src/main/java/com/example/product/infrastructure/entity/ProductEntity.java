package com.example.product.infrastructure.entity;

import com.example.product.domain.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer stock;

    @Builder
    public ProductEntity(Long id, String name, Integer stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }


    public Product toDomain() {
        return Product.
                builder().
                id(id).
                name(name).
                stock(stock).
                build();
    }

    @EventListener
    @Transactional
    public int increaseStock(int stock) {
        this.stock += stock;

        return stock;
    }

    @EventListener
    @Transactional
    public int decreaseStock(int stock) {
        this.stock -= stock;
        if (this.stock < 0)
            this.stock = 0;
        return this.stock;
    }
}
