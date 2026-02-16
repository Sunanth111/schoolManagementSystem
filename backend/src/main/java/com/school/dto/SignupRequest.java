package com.school.dto;

import lombok.Data;

@Data
public class SignupRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String role; // ADMIN, TEACHER, STUDENT, PARENT
    private String schoolCode; // For multi-tenant
    private String studentId; // If signing up as student
    private String parentId; // If signing up as parent
    private String teacherId; // If signing up as teacher
}

