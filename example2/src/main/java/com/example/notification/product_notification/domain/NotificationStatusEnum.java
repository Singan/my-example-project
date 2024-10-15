package com.example.notification.product_notification.domain;

public enum NotificationStatusEnum {

    IN_PROGRESS("발송 중"),
    CANCELED_BY_SOLD_OUT("품절에 의한 발송 중단"),
    CANCELED_BY_ERROR("예외에 의한 발송 중단"),
    COMPLETED("완료");

    private final String description;

    NotificationStatusEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

