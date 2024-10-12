package com.example.product.domain;

import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Product {
    private Long id;

    private String name;

    @Min(0)
    private Integer stock;
    @Builder
    public Product(Long id, String name, Integer stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }
}
