package com.example.product.service;

import com.example.product.service.dto.request.ProductDecreaseDto;
import com.example.product.domain.Product;
import com.example.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void productSave() {
        for (int i = 1; i <= 5; i++) {

            Product product = Product.
                    builder().
                    name("상품" + i).
                    restockRound(0).
                    stock(generateRandomStock()).
                    build();
            productRepository.save(product);
        }
    }

    // 랜덤 재고 생성 메서드
    private int generateRandomStock() {
        return ThreadLocalRandom.current().nextInt(300, 501);
    }

    @Override
    public int productRestock(long id, int stock) {
        return productRepository.productRestock(id, stock);
    }

    @Override
    @Transactional
    public void productDecrease(ProductDecreaseDto productDecreaseDto) {
        productRepository.productDecrease(productDecreaseDto.productId(), productDecreaseDto.decreaseStock());
    }

}
