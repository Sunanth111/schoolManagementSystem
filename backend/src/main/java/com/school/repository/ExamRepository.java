package com.school.repository;

import com.school.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findBySchoolClassId(Long classId);
    List<Exam> findByCourseId(Long courseId);
    List<Exam> findByExamDate(LocalDate date);
    List<Exam> findByStatus(Exam.Status status);
}

