package com.example.notification.product_user_notification.service;

import com.example.notification.product_notification.domain.ProductNotification;
import com.example.notification.product_notification.domain.ProductNotificationEntity;
import com.example.notification.product_notification.service.ProductNotificationService;
import com.example.notification.product_user_notification.domain.NotificationUser;
import com.example.notification.product_user_notification.domain.NotificationUserHistoryEntity;
import com.example.notification.product_user_notification.repository.JpaNotificationUserHistoryRepository;
import com.google.common.util.concurrent.RateLimiter;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

@Component
@RequiredArgsConstructor
@EnableAsync
@Slf4j
public class NotificationManager {
    private final BlockingQueue<ProductNotificationEntity> taskQueue = new LinkedBlockingQueue<>();

    private final NotificationService notificationService;

    private final ConcurrentHashMap<Long, Boolean> productStockChkMap = new ConcurrentHashMap<>();

    private final JpaNotificationUserHistoryRepository jpaNotificationUserHistoryRepository;
    private final ProductNotificationService productNotificationService;

    private final RateLimiter rateLimiter = RateLimiter.create(1);


    @EventListener(ApplicationReadyEvent.class)
    public void execute() {
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    ProductNotificationEntity productNotification = taskQueue.take();
                    processNotification(productNotification);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.run();
    }

    private void processNotification(ProductNotificationEntity productNotification) {
        Long productId = productNotification.getProductId();
        boolean stockChk;
        List<NotificationUser> notificationUserEntities = notificationService.findByProductId(productId);
        Queue<NotificationUser> notificationUserQueue = new LinkedList<>(notificationUserEntities);
        NotificationUser lastUser;
        ArrayList<NotificationUserHistoryEntity> historyEntityArrayList = new ArrayList<>();
        // 재고 체크 로직
        while (stockChk = productStockChkMap.getOrDefault(productId, false) && !notificationUserQueue.isEmpty()) {
            lastUser = notificationUserQueue.poll();
            log.info("productId : " + lastUser.getProductId() + " userId : " + lastUser.getUserId());
            NotificationUserHistoryEntity historyEntity = NotificationUserHistoryEntity.builder()
                    .userId(lastUser.getUserId())
                    .createDateTime(LocalDateTime.now())
                    .restockRound(productNotification.getRestockRound())
                    .productId(lastUser.getProductId())
                    .build();

            // historyEntity를 DB에 저장하기 위한 로직 추가
            historyEntityArrayList.add(historyEntity);
            rateLimiter.acquire();
        }
        ProductNotificationEntity update =
                productNotificationService.
                        findByProductNotification(productNotification.getId());
        // 상태 업데이트
        if (!stockChk) {
            update.soldOut();
        } else if (!notificationUserQueue.isEmpty()) {
            productNotification.exception();
        } else {
            productNotification.completed();
        }


        jpaNotificationUserHistoryRepository.saveAll(historyEntityArrayList);
        productStockChkMap.remove(productId);
    }

    public void pushNotification(ProductNotificationEntity productNotification) {
        taskQueue.add(productNotification);
    }

    public void pushStock(Long productId) {
        productStockChkMap.putIfAbsent(productId, true);
    }
}
