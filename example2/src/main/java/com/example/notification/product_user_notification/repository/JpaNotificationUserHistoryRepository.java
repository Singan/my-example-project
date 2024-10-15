package com.example.notification.product_user_notification.repository;

import com.example.notification.product_user_notification.domain.NotificationUserHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaNotificationUserHistoryRepository extends JpaRepository<NotificationUserHistoryEntity, Long> {
}
