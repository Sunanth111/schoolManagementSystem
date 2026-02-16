package com.school.service;

import com.school.entity.Notification;
import com.school.entity.School;
import com.school.entity.User;
import com.school.repository.NotificationRepository;
import com.school.repository.SchoolRepository;
import com.school.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    public Notification createNotification(Notification notification) {
        if (notification.getSender() != null && notification.getSender().getId() != null) {
            User sender = userRepository.findById(notification.getSender().getId())
                    .orElseThrow(() -> new RuntimeException("Sender not found"));
            notification.setSender(sender);
        }

        if (notification.getRecipient() != null && notification.getRecipient().getId() != null) {
            User recipient = userRepository.findById(notification.getRecipient().getId())
                    .orElseThrow(() -> new RuntimeException("Recipient not found"));
            notification.setRecipient(recipient);
        }

        if (notification.getSchool() != null && notification.getSchool().getId() != null) {
            School school = schoolRepository.findById(notification.getSchool().getId())
                    .orElseThrow(() -> new RuntimeException("School not found"));
            notification.setSchool(school);
        }

        return notificationRepository.save(notification);
    }

    public List<Notification> getNotificationsByRecipient(Long recipientId) {
        return notificationRepository.findByRecipientId(recipientId);
    }

    public List<Notification> getUnreadNotifications(Long recipientId) {
        return notificationRepository.findByRecipientIdAndReadFalse(recipientId);
    }

    public Notification markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setRead(true);
        notification.setReadAt(java.time.LocalDateTime.now());
        return notificationRepository.save(notification);
    }
}

