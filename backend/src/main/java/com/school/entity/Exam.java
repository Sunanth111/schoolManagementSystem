package com.school.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "exams")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String examName;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "school_class_id", nullable = false)
    private SchoolClass schoolClass;

    @Column(nullable = false)
    private LocalDate examDate;

    private LocalTime startTime;
    private LocalTime endTime;
    private Integer duration; // in minutes
    private Integer totalMarks;
    private Integer passingMarks;
    private String examType; // Midterm, Final, Quiz, Assignment, Project
    private String instructions;
    private String roomNumber;

    @ManyToOne
    @JoinColumn(name = "conducted_by_id")
    private Teacher conductedBy;

    @Enumerated(EnumType.STRING)
    private Status status = Status.SCHEDULED;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    public enum Status {
        SCHEDULED, ONGOING, COMPLETED, CANCELLED, POSTPONED
    }
}

