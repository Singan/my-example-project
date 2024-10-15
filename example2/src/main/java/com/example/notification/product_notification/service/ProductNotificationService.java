package com.example.notification.product_notification.service;

import com.example.notification.product_notification.domain.NotificationStatusEnum;
import com.example.notification.product_notification.domain.ProductNotificationEntity;
import com.example.notification.product_notification.repository.JpaProductNotificationRepository;
import com.example.notification.product_user_notification.domain.NotificationUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductNotificationService {
    private final JpaProductNotificationRepository productNotificationRepository;

    @Transactional
    public ProductNotificationEntity productNotificationSetting(Long productId, int restockRound) {
        ProductNotificationEntity entity = ProductNotificationEntity.builder()
                .notificationStatusEnum(NotificationStatusEnum.IN_PROGRESS)
                .restockRound(restockRound)
                .productId(productId)
                .build();
        return productNotificationRepository.save(entity);
    }
    public ProductNotificationEntity findProductNotificationSetting(Long productId){
        return productNotificationRepository.findById(productId).get();
    }

    public void update(Long id, NotificationStatusEnum statusEnum, Long userId) {
        productNotificationRepository.updateStatusAndLastUser(id, statusEnum, userId);
    }
}
