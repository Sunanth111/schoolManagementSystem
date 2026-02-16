package com.school.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "fees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Double paidAmount = 0.0;

    @Column(nullable = false)
    private LocalDate dueDate;

    private LocalDate paidDate;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    private String feeType; // Tuition, Library, Lab, Sports, etc.
    private String description;
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum Status {
        PENDING, PAID, PARTIAL, OVERDUE
    }
}

