package com.example.notification.product_user_notification.domain.exception;

public class SoldOutException extends RuntimeException {

    public SoldOutException() {
        super("The product is sold out.");
    }

    public SoldOutException(String message) {
        super(message);
    }

    public SoldOutException(String message, Throwable cause) {
        super(message, cause);
    }
}
