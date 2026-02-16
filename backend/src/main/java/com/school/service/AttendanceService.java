package com.school.service;

import com.school.entity.Attendance;
import com.school.entity.Course;
import com.school.entity.SchoolClass;
import com.school.entity.Student;
import com.school.repository.AttendanceRepository;
import com.school.repository.CourseRepository;
import com.school.repository.SchoolClassRepository;
import com.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    public Attendance markAttendance(Attendance attendance) {
        if (attendance.getStudent() != null && attendance.getStudent().getId() != null) {
            Student student = studentRepository.findById(attendance.getStudent().getId())
                    .orElseThrow(() -> new RuntimeException("Student not found"));
            attendance.setStudent(student);
        }

        if (attendance.getSchoolClass() != null && attendance.getSchoolClass().getId() != null) {
            SchoolClass schoolClass = schoolClassRepository.findById(attendance.getSchoolClass().getId())
                    .orElseThrow(() -> new RuntimeException("Class not found"));
            attendance.setSchoolClass(schoolClass);
        }

        if (attendance.getCourse() != null && attendance.getCourse().getId() != null) {
            Course course = courseRepository.findById(attendance.getCourse().getId())
                    .orElseThrow(() -> new RuntimeException("Course not found"));
            attendance.setCourse(course);
        }

        if (attendance.getDate() == null) {
            attendance.setDate(LocalDate.now());
        }

        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAttendanceByStudent(Long studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }

    public List<Attendance> getAttendanceByClassAndDate(Long classId, LocalDate date) {
        return attendanceRepository.findBySchoolClassIdAndDate(classId, date);
    }
}

