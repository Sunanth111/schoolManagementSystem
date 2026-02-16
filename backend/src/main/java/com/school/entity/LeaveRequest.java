package com.school.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "leave_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "applicant_id", nullable = false)
    private User applicant;

    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    private Integer numberOfDays;
    private String reason;
    private String remarks;

    @ManyToOne
    @JoinColumn(name = "approved_by_id")
    private User approvedBy;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    private LocalDateTime appliedAt = LocalDateTime.now();
    private LocalDateTime reviewedAt;
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum LeaveType {
        SICK_LEAVE, CASUAL_LEAVE, EMERGENCY_LEAVE, VACATION, PERSONAL, OTHER
    }

    public enum Status {
        PENDING, APPROVED, REJECTED, CANCELLED
    }
}

