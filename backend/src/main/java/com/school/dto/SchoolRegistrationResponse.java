package com.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SchoolRegistrationResponse {
    private String schoolCode;
    private String schoolName;
    private String message;
    private AuthResponse adminAccount;
}

