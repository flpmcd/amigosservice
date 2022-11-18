package com.amigoscode.notification;

import com.amigoscode.clients.notification.dto.NotificationRequest;
import com.amigoscode.clients.notification.dto.NotificationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/notifications")
public record NotificationController(NotificationService service) {

    @PostMapping
    public NotificationResponse sendNotification(@RequestBody NotificationRequest request) {

        Notification notification = service.sendNotification(Notification.builder()
                .message(request.message())
                .build());
        return new NotificationResponse(notification.getMessage());
    }
}


