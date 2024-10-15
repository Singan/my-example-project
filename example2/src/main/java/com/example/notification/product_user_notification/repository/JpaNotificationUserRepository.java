package com.example.notification.product_user_notification.repository;

import com.example.notification.product_user_notification.domain.NotificationUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaNotificationUserRepository extends JpaRepository<NotificationUserEntity, Long> {

    List<NotificationUserEntity> findAllByProductIdAndActivatedIsTrue(Long productId);


    List<NotificationUserEntity> findAllByProductIdAndUserIdGreaterThanAndActivatedIsTrue(Long productId, Long userId);
}
