package com.amigoscode.notification;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record NotificationService(NotificationRepository repository) {
    public Notification sendNotification(Notification notification) {
        notification.setCreatedAt(LocalDateTime.now());
        return repository.save(notification);
    }
}

