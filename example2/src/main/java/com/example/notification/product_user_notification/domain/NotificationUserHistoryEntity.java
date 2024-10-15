package com.example.notification.product_user_notification.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "ProductUserNotificationHistory")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotificationUserHistoryEntity {
    //1. 상품 아이디
    //2. 유저 아이디
    //3. 재입고 회차
    //4. 발송 날짜
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long productId;
    private Long userId;

    private Integer restockRound;

    @CreatedDate
    private LocalDateTime createDateTime;
    @Builder
    public NotificationUserHistoryEntity(Long id, Long productId, Long userId, Integer restockRound, LocalDateTime createDateTime) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.restockRound = restockRound;
        this.createDateTime = createDateTime;
    }
}
