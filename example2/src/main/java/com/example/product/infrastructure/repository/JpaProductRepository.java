package com.example.product.infrastructure.repository;

import com.example.product.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
}
