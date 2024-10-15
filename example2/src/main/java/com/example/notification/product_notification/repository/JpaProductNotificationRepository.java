package com.example.notification.product_notification.repository;

import com.example.notification.product_notification.domain.NotificationStatusEnum;
import com.example.notification.product_notification.domain.ProductNotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface JpaProductNotificationRepository extends JpaRepository<ProductNotificationEntity, Long> {

    @Modifying

    @Query("update ProductNotificationEntity e set e.notificationStatusEnum = :status " +
            ", e.lastUserId = :lastUser where e.id = :id ")
    @Transactional
    void updateStatusAndLastUser(Long id, NotificationStatusEnum status, Long lastUser);
}
