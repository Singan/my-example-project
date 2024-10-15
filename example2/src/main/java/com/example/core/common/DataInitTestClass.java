package com.example.core.common;

import com.example.notification.product_user_notification.service.NotificationService;
import com.example.product.domain.Product;
import com.example.product.domain.ProductEntity;
import com.example.product.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
public class DataInitTestClass {
    private final ProductRepository productRepository;
    private final NotificationService notificationService;
    @PostConstruct
    public void productSave() {
//        for (int i = 1; i <= 2; i++) {
//            Product product = Product.
//                    builder().
//                    name("상품" + i).
//                    restockRound(0).
//                    stock(generateRandomStock()).
//                    build();
//            productRepository.save(product);
//        }
//
//        for (long i = 1; i <= 50; i++) {
//            notificationService.notificationSetting(1L, i);
//            notificationService.notificationSetting(2L, i);
//        }

    }

    private int generateRandomStock() {
        return ThreadLocalRandom.current().nextInt(300, 501);
    }

}
