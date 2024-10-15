package com.example.notification.product_notification.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 상품 알림 내역
@Table(name = "ProductNotificationHistory")
public class ProductNotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer restockRound;

    @Enumerated
    @Column(name = "status")
    private NotificationStatusEnum notificationStatusEnum;

    private Long productId;

    private Long lastUserId;

    //1. 상품 아이디
    //2. 재입고 회차
    //3. 재입고 알림 발송 상태
    //4. 마지막 발송 유저 아이디

    @Builder
    public ProductNotificationEntity(Long id, Integer restockRound, NotificationStatusEnum notificationStatusEnum, Long productId, Long lastUserId) {
        this.id = id;
        this.restockRound = restockRound;
        this.notificationStatusEnum = notificationStatusEnum;
        this.productId = productId;
        this.lastUserId = lastUserId;
    }

    public void completed(){
        notificationStatusEnum = NotificationStatusEnum.COMPLETED;
    }
    public void exception(){
        notificationStatusEnum = NotificationStatusEnum.CANCELED_BY_ERROR;
    }
    public void soldOut(){
        notificationStatusEnum = NotificationStatusEnum.CANCELED_BY_SOLD_OUT;
    }
}
