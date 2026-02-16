package com.school.service;

import com.school.entity.Fee;
import com.school.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Autowired
    private FeeRepository feeRepository;

    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        
        stats.put("totalStudents", studentRepository.findByActiveTrue().size());
        stats.put("totalTeachers", teacherRepository.findByActiveTrue().size());
        stats.put("totalCourses", courseRepository.findByActiveTrue().size());
        stats.put("totalClasses", schoolClassRepository.findByActiveTrue().size());
        
        // Fee statistics
        long totalFees = feeRepository.count();
        long paidFees = feeRepository.findByStatus(Fee.Status.PAID).size();
        long pendingFees = feeRepository.findByStatus(Fee.Status.PENDING).size();
        long overdueFees = feeRepository.findByStatus(Fee.Status.OVERDUE).size();
        
        stats.put("totalFees", totalFees);
        stats.put("paidFees", paidFees);
        stats.put("pendingFees", pendingFees);
        stats.put("overdueFees", overdueFees);
        
        // Calculate total revenue
        double totalRevenue = feeRepository.findAll().stream()
                .mapToDouble(f -> f.getPaidAmount() != null ? f.getPaidAmount() : 0.0)
                .sum();
        stats.put("totalRevenue", totalRevenue);
        
        return stats;
    }
}

