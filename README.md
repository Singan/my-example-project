# 과제평가

작성일시: 2024년 10월 12일 오후 12:15
분류: 프로젝트

- 재입고 알림을 전송하기 전, 상품의 재입고 회차를 1 증가 시킨다.
    - 재입고 회차를 버전처럼 사용할 수 있을 것 같다.
- 재입고 알림은 재입고 알림을 설정한 유저 순서대로 메시지를 전송한다.
    - 알림을 한번에 모두에게 보내면 안될 것 같다.
- 회차별 재입고 알림을 받은 유저 목록을 저장해야 한다.
- 재입고 알림을 보내던 중 재고가 모두 없어진다면 알림 보내는 것을 중단합니다.
    - 알림을 보낼때마다 재고량을 체크해야 할 것이다.
- 재입고 알림 전송의 상태를 DB 에 저장해야 한다.
    - IN_PROGRESS (발송 중)
    - CANCELED_BY_SOLD_OUT (품절에 의한 발송 중단)
    - CANCELED_BY_ERROR (예외에 의한 발송 중단)
    - COMPLETED (완료)
- 설계해야 할 테이블 목록
    1. Product (상품)
        1. 상품 아이디
        2. 재입고 회차
        3. 재고 상태
    2. ProductNotificationHistory (상품별 재입고 알림 히스토리)
        1. 상품 아이디
        2. 재입고 회차
        3. 재입고 알림 발송 상태
        4. 마지막 발송 유저 아이디
    3. ProductUserNotification (상품별 재입고 알림을 설정한 유저)
        1. 상품 아이디
        2. 유저 아이디
        3. 활성화 여부
        4. 생성 날짜
        5. 수정 날짜
    4. ProductUserNotificationHistory (상품 + 유저별 알림 히스토리)
        1. 상품 아이디
        2. 유저 아이디
        3. 재입고 회차
        4. 발송 날짜
- 재입고 API
    - 재입고 API 요청 시 알림 발송을 시작한다.
    - stock 을 어떻게 반영할 것인가.
- 상품 구매 API
    - 스톡을 줄인다. 즉 이 값이 재입고 API 에 반영되어야한다.
- 유저 재입고 알림 신청 API
    - 유저가 ProducUserNotification 에 추가된다.

---

### 인덱스

```java
    List<NotificationUserEntity> findAllByProductIdAndActivatedIsTrue(Long productId);
    List<NotificationUserEntity> findAllByProductIdAndUserIdGreaterThanAndActivatedIsTrue(Long productId, Long userId);
```

NotificationUserEntity = ProductUserNotification  테이블이다. 

ProductId,UserId,Activated 를 인덱스로 세우거나

ProductId,UserId 만 인덱스로 잡아도 Activated는 가져온 데이터 중 거르는 작업을 거칠 것이다.

---

### 로직

스톡을 따로 관리하며 ↔ 알림 매니저에서 스톡을 관찰해야한다.

알림 매니저는 시작 시점에 execute 한다.

```java
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

```

알림 매니저에 큐를 가지고 있다.

리스톡된 번호를 리퀘스트에서 큐에 담는다.

```java
    @PostMapping("/{productId}/notifications/re-stock")
    public void productRestock(@PathVariable Long productId, @RequestBody ProductRestockRequestDto requestDto) {
        int restockRound = productService.productRestock(productId, requestDto.increaseStock());
        ProductNotificationEntity productNotification =productNotificationService.productNotificationSetting(productId ,restockRound);
        notificationManager.pushStock(productId);
        notificationManager.pushNotification(productNotification);
    }
```

큐는 번호를 꺼내 들어온 요청의 재입고 알림 신청을 전부 가져온다. → 

```java
        Long productId = productNotification.getProductId();
        boolean stockChk;
        List<NotificationUser> notificationUserEntities;
        if (productNotification.getLastUserId() == null) {
            notificationUserEntities = notificationService.findByProductId(productId);
        } else {
            notificationUserEntities = notificationService.manualSendUser(productId);
        }
```

재입고 알림 신청을 while 문을 돌며 List 에 저장할 내역을 모은다.(한번에 처리하기 위하여)

이 때 알림 매니저에서 ConcurrentHashMap 으로 <ProductId , Boolean> 으로 관리되는 현재 품절여부를 체크한다. True 라면 while 문을 돌고 객체를 만들어 리스트에 적재한다.

만약 LastUserId 가 있다면 Manual 이고 없다면 리스톡이다.(리스톡 했을 경우 Manual 해야하는 알림이 쌓여 있더라도 productNotification 객체를 새로 만들기에 리스톡엔 무조건 null이다.

```java
            historyEntityArrayList.add(historyEntity);
            rateLimiter.acquire();
        }

        // 상태 업데이트
        if (!stockChk) {
            productNotification.soldOut();
        } else if (!notificationUserQueue.isEmpty()) {
            productNotification.exception();
        } else {
            productNotification.completed();
        }
        productNotificationService.
                update(productNotification.getId(),
                        productNotification.getNotificationStatusEnum(),
                        lastUserId);

        jpaNotificationUserHistoryRepository.saveAll(historyEntityArrayList);
        productStockChkMap.remove(productId);
```

stockChk 가 false 면 soldOut , true 지만 가져온 유저를 모두 처리 못했다면 exception 모두 처리했다면 completed 로 상태 값을 변경해준다. 이를 업데이트하고 유저 알림 리스트를 한 번에 저장한 후 stockChk 값을 지운다.