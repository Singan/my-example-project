package com.example.notification.product_user_notification.domain;

import com.example.core.common.infrastructure.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ProductUserNotification")
public class NotificationUserEntity extends BaseTimeEntity {
    //3. ProductUserNotification (상품별 재입고 알림을 설정한 유저)
    //    1. 상품 아이디
    //    2. 유저 아이디
    //    3. 활성화 여부
    //    4. 생성 날짜
    //    5. 수정 날짜

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long productId;
    private Long userId;

    private Boolean activated;

    @Builder
    public NotificationUserEntity(Long id, Long productId, Long userId, Boolean activated) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.activated = activated;
    }

    public static NotificationUserEntity from(NotificationUser notificationUser) {
        return NotificationUserEntity
                .builder()
                .id(notificationUser.getId())
                .userId(notificationUser.getId())
                .activated(notificationUser.getActivated())
                .productId(notificationUser.getProductId())
                .build();
    }

    public NotificationUser toDomain() {
        return NotificationUser.builder()
                .id(id)
                .userId(userId)
                .activated(activated)
                .productId(productId)
                .modifiedDateTime(getCreateDateTime())
                .modifiedDateTime(getModifiedDateTime())
                .build();
    }

}
