package com.example.notification.product_user_notification.controller;

import com.example.notification.product_user_notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;


    @PostMapping
    public void notificationSetting(NotificationUserSettingDto notificationUserSettingDto) {// 유저가 하나의 상품에 알림 설정
        notificationService.notificationSetting(
                notificationUserSettingDto.productId, 
                notificationUserSettingDto.userId
        );
    }

    record NotificationUserSettingDto(Long productId, Long userId) {
    }

}
