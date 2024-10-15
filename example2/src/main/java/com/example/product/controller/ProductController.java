package com.example.product.controller;

import com.example.notification.product_notification.domain.ProductNotificationEntity;
import com.example.notification.product_notification.service.ProductNotificationService;
import com.example.notification.product_user_notification.domain.NotificationUser;
import com.example.notification.product_user_notification.service.NotificationManager;
import com.example.notification.product_user_notification.service.NotificationService;
import com.example.notification.product_user_notification.domain.NotificationUserEntity;
import com.example.product.service.ProductService;
import com.example.product.controller.dto.ProductRequestDecreaseDto;
import com.example.product.controller.dto.ProductRestockRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    private final ProductNotificationService productNotificationService;
    private final NotificationManager notificationManager;

    @PostMapping
    public void saveProduct() {
        productService.productSave();
    }

    @PutMapping()
    public void productDecrease(ProductRequestDecreaseDto requestDecreaseDto) {
        productService.productDecrease(requestDecreaseDto.getProductDecreaseDto());
    }

    @PostMapping("/{productId}/notifications/re-stock")
    public void productRestock(@PathVariable Long productId, @RequestBody ProductRestockRequestDto requestDto) {
        int restockRound = productService.productRestock(productId, requestDto.increaseStock());
        ProductNotificationEntity productNotification =productNotificationService.productNotificationSetting(productId ,restockRound);
        notificationManager.pushStock(productId);
        notificationManager.pushNotification(productNotification);
    }
}
