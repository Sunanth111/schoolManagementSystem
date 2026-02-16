package com.school.repository;

import com.school.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStudentIdAndDate(Long studentId, LocalDate date);
    List<Attendance> findBySchoolClassIdAndDate(Long classId, LocalDate date);
    List<Attendance> findByStudentId(Long studentId);
}

