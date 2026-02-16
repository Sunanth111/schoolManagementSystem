package com.school.service;

import com.school.entity.Teacher;
import com.school.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findByActiveTrue();
    }

    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    public Teacher createTeacher(Teacher teacher) {
        if (teacher.getTeacherId() == null || teacher.getTeacherId().isEmpty()) {
            teacher.setTeacherId("TCH" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        }
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Long id, Teacher teacherDetails) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        
        teacher.setFirstName(teacherDetails.getFirstName());
        teacher.setLastName(teacherDetails.getLastName());
        teacher.setEmail(teacherDetails.getEmail());
        teacher.setPhone(teacherDetails.getPhone());
        teacher.setDateOfBirth(teacherDetails.getDateOfBirth());
        teacher.setAddress(teacherDetails.getAddress());
        teacher.setGender(teacherDetails.getGender());
        teacher.setQualification(teacherDetails.getQualification());
        teacher.setSpecialization(teacherDetails.getSpecialization());
        
        if (teacherDetails.getCourses() != null) {
            teacher.setCourses(teacherDetails.getCourses());
        }
        
        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        teacher.setActive(false);
        teacherRepository.save(teacher);
    }
}

