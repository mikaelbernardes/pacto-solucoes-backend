package com.pacto_solucoes.recruitment.controllers;

import com.pacto_solucoes.recruitment.domain.Notification;
import com.pacto_solucoes.recruitment.service.NotificationService;
import com.pacto_solucoes.recruitment.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/{userId}")
    public List<Notification> getUserNotifications(@PathVariable Long userId) {
        return notificationService.getNotificationsForUser(userId);
    }

    @PutMapping("/mark-as-read/{notificationId}")
    public void markNotificationAsRead(@PathVariable Long notificationId) {
        notificationService.markNotificationAsRead(notificationId);
    }

}
