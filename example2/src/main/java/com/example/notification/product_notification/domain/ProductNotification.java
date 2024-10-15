package com.example.notification.product_notification.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
public class ProductNotification {
    private Long id;

    private Integer restockRound;
    private NotificationStatusEnum notificationStatusEnum;
    private Long productId;
    private Long lastUserId;
}
