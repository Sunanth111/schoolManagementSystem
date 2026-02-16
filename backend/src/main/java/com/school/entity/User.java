package com.school.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    private String firstName;
    private String lastName;
    private String phone;
    
    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;
    
    private boolean active = true;
    private boolean emailVerified = false;
    private String verificationToken;
    private LocalDateTime emailVerifiedAt;
    private LocalDateTime lastLoginAt;
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum Role {
        SUPER_ADMIN, ADMIN, TEACHER, STUDENT, PARENT
    }
}

