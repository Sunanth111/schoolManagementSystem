package com.school.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 1000)
    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @Enumerated(EnumType.STRING)
    private Priority priority = Priority.NORMAL;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    private boolean read = false;
    private LocalDateTime readAt;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime scheduledAt;
    private boolean active = true;

    public enum NotificationType {
        ANNOUNCEMENT, EXAM, FEE, ATTENDANCE, GRADE, LEAVE, GENERAL
    }

    public enum Priority {
        LOW, NORMAL, HIGH, URGENT
    }
}

