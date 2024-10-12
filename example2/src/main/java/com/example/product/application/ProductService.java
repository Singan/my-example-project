package com.example.product.application;

import com.example.product.application.dto.request.ProductDecreaseDto;
import com.example.product.infrastructure.entity.ProductEntity;
import com.example.product.infrastructure.repository.JpaProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final JpaProductRepository jpaProductRepository;

    @Transactional
    public void productSave() {
        for (int i = 1; i <= 5; i++) {

            ProductEntity product = ProductEntity.builder().name("상품" + i).stock(generateRandomStock()).build();
            jpaProductRepository.save(product);
        }
    }

    @Transactional
    public void productDecrease(ProductDecreaseDto productDecreaseDto) {

    }
    // 랜덤 재고 생성 메서드
    private int generateRandomStock() {
        return ThreadLocalRandom.current().nextInt(300, 501);
    }
}
