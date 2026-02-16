package com.school.dto;

import lombok.Data;

@Data
public class SchoolRegistrationRequest {
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
    private String schoolType;
    private String affiliation;
    
    // Admin account details
    private String adminFirstName;
    private String adminLastName;
    private String adminEmail;
    private String adminPassword;
    private String adminPhone;
}

