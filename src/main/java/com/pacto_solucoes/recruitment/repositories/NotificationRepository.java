package com.pacto_solucoes.recruitment.repositories;

import com.pacto_solucoes.recruitment.domain.Notification;
import com.pacto_solucoes.recruitment.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Notification n SET n.isRead = :isRead WHERE n.id = :notificationId")
    void markAsRead(@Param("notificationId") Long notificationId, @Param("isRead") boolean isRead);


    List<Notification> findByUserId(Long userId);
}