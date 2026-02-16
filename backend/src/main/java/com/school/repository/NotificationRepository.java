package com.school.repository;

import com.school.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByRecipientId(Long recipientId);
    List<Notification> findByRecipientIdAndReadFalse(Long recipientId);
    List<Notification> findBySchoolId(Long schoolId);
    List<Notification> findByType(Notification.NotificationType type);
}

