package com.example.product.domain;

import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Product {
    private Long id;

    private Integer restockRound;

    private String name;

    @Min(0)
    private Integer stock;
    @Builder
    public Product(Long id, Integer restockRound, Integer stock , String name) {
        this.id = id;
        this.restockRound = restockRound;
        this.stock = stock;
        this.name = name;
    }
}
