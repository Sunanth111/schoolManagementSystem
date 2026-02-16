package com.school.controller;

import com.school.entity.Exam;
import com.school.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
@CrossOrigin(origins = "*")
public class ExamController {

    @Autowired
    private ExamService examService;

    @PostMapping
    public Exam createExam(@RequestBody Exam exam) {
        return examService.createExam(exam);
    }

    @GetMapping("/class/{classId}")
    public List<Exam> getExamsByClass(@PathVariable Long classId) {
        return examService.getExamsByClass(classId);
    }

    @GetMapping("/course/{courseId}")
    public List<Exam> getExamsByCourse(@PathVariable Long courseId) {
        return examService.getExamsByCourse(courseId);
    }
}

