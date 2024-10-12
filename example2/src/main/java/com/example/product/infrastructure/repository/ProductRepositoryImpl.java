package com.example.product.infrastructure.repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductRepositoryImpl {

    private final JpaProductRepository jpaProductRepository;


    public void productRestock(Long productId , Long stock){

    }
}
