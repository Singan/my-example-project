package com.example.notification.product_user_notification.service;

import com.example.notification.product_notification.domain.ProductNotificationEntity;
import com.example.notification.product_user_notification.domain.NotificationUser;

import java.util.List;

public interface NotificationService {
    void notificationSetting(Long productId, Long userId);

    List<NotificationUser> findByProductId(Long productId);
}
