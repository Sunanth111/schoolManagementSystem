package com.school.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "teachers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String teacherId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private String address;
    private String gender;
    private String qualification;
    private String specialization;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
        name = "teacher_courses",
        joinColumns = @JoinColumn(name = "teacher_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;

    private String employeeId;
    private String department;
    private String designation; // Principal, Vice Principal, Head Teacher, Teacher
    private Double salary;
    private String bankAccount;
    private String photoUrl;
    private Integer experienceYears;
    private String previousInstitution;
    private LocalDate joiningDate;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
    private String employmentType; // Full-time, Part-time, Contract
    private Integer remainingLeaveDays;

    private LocalDateTime joinedDate = LocalDateTime.now();
    private boolean active = true;
}

