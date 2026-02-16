package com.school.service;

import com.school.config.JwtUtil;
import com.school.dto.AuthResponse;
import com.school.dto.SchoolRegistrationRequest;
import com.school.dto.SchoolRegistrationResponse;
import com.school.dto.SignupRequest;
import com.school.entity.*;
import com.school.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RegistrationService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private SchoolService schoolService;

    @Transactional
    public SchoolRegistrationResponse registerSchoolWithAdmin(SchoolRegistrationRequest request) {
        // Create School
        School school = new School();
        school.setSchoolName(request.getSchoolName());
        school.setEmail(request.getEmail());
        school.setPhone(request.getPhone());
        school.setAddress(request.getAddress());
        school.setCity(request.getCity());
        school.setState(request.getState());
        school.setCountry(request.getCountry());
        school.setPostalCode(request.getPostalCode());
        school.setWebsite(request.getWebsite());
        school.setPrincipalName(request.getPrincipalName());
        school.setPrincipalEmail(request.getPrincipalEmail());
        school.setPrincipalPhone(request.getPrincipalPhone());
        school.setSchoolType(request.getSchoolType());
        school.setAffiliation(request.getAffiliation());

        school = schoolService.registerSchool(school);

        // Create Admin User
        User admin = new User();
        admin.setEmail(request.getAdminEmail());
        admin.setPassword(passwordEncoder.encode(request.getAdminPassword()));
        admin.setRole(User.Role.ADMIN);
        admin.setFirstName(request.getAdminFirstName());
        admin.setLastName(request.getAdminLastName());
        admin.setPhone(request.getAdminPhone());
        admin.setSchool(school);
        admin.setActive(true);
        admin.setEmailVerified(true);

        admin = userRepository.save(admin);

        // Generate token for admin
        String token = jwtUtil.generateToken(admin.getEmail(), admin.getRole().name());
        String name = admin.getFirstName() + " " + admin.getLastName();
        AuthResponse authResponse = new AuthResponse(token, admin.getEmail(), admin.getRole().name(), name);

        return new SchoolRegistrationResponse(
                school.getSchoolCode(),
                school.getSchoolName(),
                "School registered successfully!",
                authResponse
        );
    }

    @Transactional
    public AuthResponse signup(SignupRequest request) {
        // Validate school exists if schoolCode provided
        School school = null;
        if (request.getSchoolCode() != null && !request.getSchoolCode().isEmpty()) {
            school = schoolRepository.findBySchoolCode(request.getSchoolCode())
                    .orElseThrow(() -> new RuntimeException("Invalid school code"));
        }

        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        // Create User
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(User.Role.valueOf(request.getRole()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhone(request.getPhone());
        user.setSchool(school);
        user.setActive(true);
        user.setEmailVerified(false);

        user = userRepository.save(user);

        // Link to Student/Teacher/Parent if IDs provided
        if (request.getRole().equals("STUDENT") && request.getStudentId() != null) {
            Student student = studentRepository.findByStudentId(request.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student not found"));
            student.setUser(user);
            studentRepository.save(student);
        } else if (request.getRole().equals("TEACHER") && request.getTeacherId() != null) {
            Teacher teacher = teacherRepository.findByTeacherId(request.getTeacherId())
                    .orElseThrow(() -> new RuntimeException("Teacher not found"));
            teacher.setUser(user);
            teacherRepository.save(teacher);
        } else if (request.getRole().equals("PARENT") && request.getParentId() != null) {
            Parent parent = parentRepository.findByParentId(request.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent not found"));
            parent.setUser(user);
            parentRepository.save(parent);
        }

        // Generate token
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        String name = user.getFirstName() + " " + user.getLastName();
        return new AuthResponse(token, user.getEmail(), user.getRole().name(), name);
    }
}

