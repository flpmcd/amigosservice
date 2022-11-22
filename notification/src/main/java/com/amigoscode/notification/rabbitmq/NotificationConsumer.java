package com.amigoscode.notification.rabbitmq;

import com.amigoscode.clients.notification.dto.NotificationRequest;
import com.amigoscode.notification.Notification;
import com.amigoscode.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService service;

    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    public void consumer(NotificationRequest notificationRequest) {
        log.info("Consumed {} from queue", notificationRequest);

        Notification notification = Notification.builder()
                .id(notificationRequest.id())
                .createdAt(LocalDateTime.now())
                .message(notificationRequest.message())
                .build();

        service.sendNotification(notification);
    }
}
