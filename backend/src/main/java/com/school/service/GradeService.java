package com.school.service;

import com.school.entity.Course;
import com.school.entity.Grade;
import com.school.entity.Student;
import com.school.repository.CourseRepository;
import com.school.repository.GradeRepository;
import com.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    public Grade createGrade(Grade grade) {
        if (grade.getStudent() != null && grade.getStudent().getId() != null) {
            Student student = studentRepository.findById(grade.getStudent().getId())
                    .orElseThrow(() -> new RuntimeException("Student not found"));
            grade.setStudent(student);
        }

        if (grade.getCourse() != null && grade.getCourse().getId() != null) {
            Course course = courseRepository.findById(grade.getCourse().getId())
                    .orElseThrow(() -> new RuntimeException("Course not found"));
            grade.setCourse(course);
        }

        // Calculate grade letter if marks provided
        if (grade.getMarks() != null && grade.getGrade() == null) {
            grade.setGrade(calculateGrade(grade.getMarks()));
        }

        return gradeRepository.save(grade);
    }

    public List<Grade> getGradesByStudent(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    public List<Grade> getGradesByCourse(Long courseId) {
        return gradeRepository.findByCourseId(courseId);
    }

    private String calculateGrade(Double marks) {
        if (marks >= 90) return "A+";
        if (marks >= 80) return "A";
        if (marks >= 70) return "B+";
        if (marks >= 60) return "B";
        if (marks >= 50) return "C+";
        if (marks >= 40) return "C";
        return "F";
    }
}

