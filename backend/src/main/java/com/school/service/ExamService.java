package com.school.service;

import com.school.entity.*;
import com.school.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public Exam createExam(Exam exam) {
        if (exam.getCourse() != null && exam.getCourse().getId() != null) {
            Course course = courseRepository.findById(exam.getCourse().getId())
                    .orElseThrow(() -> new RuntimeException("Course not found"));
            exam.setCourse(course);
        }

        if (exam.getSchoolClass() != null && exam.getSchoolClass().getId() != null) {
            SchoolClass schoolClass = schoolClassRepository.findById(exam.getSchoolClass().getId())
                    .orElseThrow(() -> new RuntimeException("Class not found"));
            exam.setSchoolClass(schoolClass);
        }

        if (exam.getConductedBy() != null && exam.getConductedBy().getId() != null) {
            Teacher teacher = teacherRepository.findById(exam.getConductedBy().getId())
                    .orElseThrow(() -> new RuntimeException("Teacher not found"));
            exam.setConductedBy(teacher);
        }

        return examRepository.save(exam);
    }

    public List<Exam> getExamsByClass(Long classId) {
        return examRepository.findBySchoolClassId(classId);
    }

    public List<Exam> getExamsByCourse(Long courseId) {
        return examRepository.findByCourseId(courseId);
    }
}

