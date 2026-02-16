package com.school.controller;

import com.school.entity.Notification;
import com.school.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationService.createNotification(notification);
    }

    @GetMapping("/recipient/{recipientId}")
    public List<Notification> getNotificationsByRecipient(@PathVariable Long recipientId) {
        return notificationService.getNotificationsByRecipient(recipientId);
    }

    @GetMapping("/recipient/{recipientId}/unread")
    public List<Notification> getUnreadNotifications(@PathVariable Long recipientId) {
        return notificationService.getUnreadNotifications(recipientId);
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<Notification> markAsRead(@PathVariable Long id) {
        try {
            Notification notification = notificationService.markAsRead(id);
            return ResponseEntity.ok(notification);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

