package com.amigoscode.clients.notification;

import com.amigoscode.clients.notification.dto.NotificationRequest;
import com.amigoscode.clients.notification.dto.NotificationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "fraud",
        url = "${clients.notification.url}"
)
public interface NotificationClient {

    @PostMapping("/api/v1/notifications")
    NotificationResponse sendNotification(@RequestBody NotificationRequest request);
}
