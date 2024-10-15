package com.example.notification.product_user_notification.service;

import com.example.notification.product_notification.domain.ProductNotificationEntity;
import com.example.notification.product_notification.repository.JpaProductNotificationRepository;
import com.example.notification.product_user_notification.domain.NotificationUser;
import com.example.notification.product_user_notification.repository.JpaNotificationUserRepository;
import com.example.notification.product_user_notification.domain.NotificationUserEntity;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final JpaNotificationUserRepository notificationUserRepository;

    private final JpaProductNotificationRepository productNotificationRepository;


    public void notificationSetting(Long productId, Long userId) {
        NotificationUserEntity notificationUser = NotificationUserEntity
                .builder()
                .productId(productId)
                .userId(userId)
                .activated(true)
                .build();
        notificationUserRepository.save(notificationUser);
    }


    public List<NotificationUser> findByProductId(Long productId) {
        return notificationUserRepository
                .findAllByProductIdAndActivatedIsTrue(productId)
                .parallelStream()
                .map(NotificationUserEntity::toDomain).toList();
    }


}
