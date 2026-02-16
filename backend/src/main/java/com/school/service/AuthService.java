package com.school.service;

import com.school.config.JwtUtil;
import com.school.dto.AuthResponse;
import com.school.dto.LoginRequest;
import com.school.entity.User;
import com.school.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthResponse login(LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        
        if (userOpt.isEmpty() || !passwordEncoder.matches(request.getPassword(), userOpt.get().getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        User user = userOpt.get();
        if (!user.isActive()) {
            throw new RuntimeException("User account is inactive");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        String name = user.getFirstName() + " " + user.getLastName();
        
        return new AuthResponse(token, user.getEmail(), user.getRole().name(), name);
    }
}

