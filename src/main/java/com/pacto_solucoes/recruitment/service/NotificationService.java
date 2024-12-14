package com.pacto_solucoes.recruitment.service;

import com.pacto_solucoes.recruitment.domain.Notification;
import com.pacto_solucoes.recruitment.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getNotificationsForUser(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    public void markNotificationAsRead(@PathVariable Long notificationId) {
        notificationRepository.markAsRead(notificationId, true);
    }
}
