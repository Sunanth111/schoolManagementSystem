package com.school.repository;

import com.school.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {
    List<Timetable> findBySchoolClassId(Long classId);
    List<Timetable> findByTeacherId(Long teacherId);
    List<Timetable> findBySchoolClassIdAndDayOfWeek(Long classId, DayOfWeek dayOfWeek);
    List<Timetable> findByActiveTrue();
}

