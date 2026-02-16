package com.school.service;

import com.school.entity.School;
import com.school.entity.User;
import com.school.repository.SchoolRepository;
import com.school.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public School registerSchool(School school) {
        // Generate unique school code
        if (school.getSchoolCode() == null || school.getSchoolCode().isEmpty()) {
            String code = generateSchoolCode(school.getSchoolName());
            school.setSchoolCode(code);
        }

        // Check if school code or email already exists
        if (schoolRepository.existsBySchoolCode(school.getSchoolCode())) {
            throw new RuntimeException("School code already exists");
        }
        if (school.getEmail() != null && schoolRepository.existsByEmail(school.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        return schoolRepository.save(school);
    }

    public List<School> getAllSchools() {
        return schoolRepository.findByActiveTrue();
    }

    public Optional<School> getSchoolById(Long id) {
        return schoolRepository.findById(id);
    }

    public Optional<School> getSchoolByCode(String code) {
        return schoolRepository.findBySchoolCode(code);
    }

    public School updateSchool(Long id, School schoolDetails) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("School not found"));

        school.setSchoolName(schoolDetails.getSchoolName());
        school.setEmail(schoolDetails.getEmail());
        school.setPhone(schoolDetails.getPhone());
        school.setAddress(schoolDetails.getAddress());
        school.setCity(schoolDetails.getCity());
        school.setState(schoolDetails.getState());
        school.setCountry(schoolDetails.getCountry());
        school.setPostalCode(schoolDetails.getPostalCode());
        school.setWebsite(schoolDetails.getWebsite());
        school.setPrincipalName(schoolDetails.getPrincipalName());
        school.setPrincipalEmail(schoolDetails.getPrincipalEmail());
        school.setPrincipalPhone(schoolDetails.getPrincipalPhone());

        return schoolRepository.save(school);
    }

    private String generateSchoolCode(String schoolName) {
        String code = schoolName.toUpperCase()
                .replaceAll("[^A-Z0-9]", "")
                .substring(0, Math.min(6, schoolName.length()));
        return code + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
    }
}

