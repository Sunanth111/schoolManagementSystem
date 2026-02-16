package com.school.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "schools")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String schoolCode;

    @Column(nullable = false)
    private String schoolName;

    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String website;
    private String principalName;
    private String principalEmail;
    private String principalPhone;
    private LocalDateTime establishedDate;
    private String schoolType; // Public, Private, International
    private String affiliation; // CBSE, ICSE, State Board, etc.
    private boolean active = true;
    private LocalDateTime registeredAt = LocalDateTime.now();
    private LocalDateTime createdAt = LocalDateTime.now();
}

