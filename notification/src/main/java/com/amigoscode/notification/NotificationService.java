package com.amigoscode.notification;

import org.springframework.stereotype.Service;

@Service
public record NotificationService(NotificationRepository repository) {
    public Notification sendNotification(Notification notification) {
        return repository.save(notification);
    }
}

