package com.school.service;

import com.school.entity.*;
import com.school.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimetableService {

    @Autowired
    private TimetableRepository timetableRepository;

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public Timetable createTimetable(Timetable timetable) {
        if (timetable.getSchoolClass() != null && timetable.getSchoolClass().getId() != null) {
            SchoolClass schoolClass = schoolClassRepository.findById(timetable.getSchoolClass().getId())
                    .orElseThrow(() -> new RuntimeException("Class not found"));
            timetable.setSchoolClass(schoolClass);
        }

        if (timetable.getCourse() != null && timetable.getCourse().getId() != null) {
            Course course = courseRepository.findById(timetable.getCourse().getId())
                    .orElseThrow(() -> new RuntimeException("Course not found"));
            timetable.setCourse(course);
        }

        if (timetable.getTeacher() != null && timetable.getTeacher().getId() != null) {
            Teacher teacher = teacherRepository.findById(timetable.getTeacher().getId())
                    .orElseThrow(() -> new RuntimeException("Teacher not found"));
            timetable.setTeacher(teacher);
        }

        return timetableRepository.save(timetable);
    }

    public List<Timetable> getTimetableByClass(Long classId) {
        return timetableRepository.findBySchoolClassId(classId);
    }

    public List<Timetable> getTimetableByTeacher(Long teacherId) {
        return timetableRepository.findByTeacherId(teacherId);
    }
}

