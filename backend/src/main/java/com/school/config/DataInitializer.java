package com.school.config;

import com.school.entity.User;
import com.school.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Create default admin user if not exists
        if (!userRepository.existsByEmail("admin@school.com")) {
            User admin = new User();
            admin.setEmail("admin@school.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(User.Role.ADMIN);
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setActive(true);
            userRepository.save(admin);
        }

        // Create default teacher user if not exists
        if (!userRepository.existsByEmail("teacher@school.com")) {
            User teacher = new User();
            teacher.setEmail("teacher@school.com");
            teacher.setPassword(passwordEncoder.encode("teacher123"));
            teacher.setRole(User.Role.TEACHER);
            teacher.setFirstName("Teacher");
            teacher.setLastName("User");
            teacher.setActive(true);
            userRepository.save(teacher);
        }
    }
}

