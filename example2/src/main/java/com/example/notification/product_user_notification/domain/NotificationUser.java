package com.example.notification.product_user_notification.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NotificationUser {
    private Long id;


    private Long productId;
    private Long userId;

    private Boolean activated;

    private LocalDateTime createDateTime;
    private LocalDateTime modifiedDateTime;
    @Builder
    public NotificationUser(Long id, Long productId, Long userId, Boolean activated, LocalDateTime createDateTime, LocalDateTime modifiedDateTime) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.activated = activated;
        this.createDateTime = createDateTime;
        this.modifiedDateTime = modifiedDateTime;
    }
}
