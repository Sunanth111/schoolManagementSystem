package com.school.service;

import com.school.entity.SchoolClass;
import com.school.entity.Student;
import com.school.repository.SchoolClassRepository;
import com.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findByActiveTrue();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student createStudent(Student student) {
        if (student.getStudentId() == null || student.getStudentId().isEmpty()) {
            student.setStudentId("STU" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        }
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        
        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setEmail(studentDetails.getEmail());
        student.setPhone(studentDetails.getPhone());
        student.setDateOfBirth(studentDetails.getDateOfBirth());
        student.setAddress(studentDetails.getAddress());
        student.setGender(studentDetails.getGender());
        
        if (studentDetails.getSchoolClass() != null && studentDetails.getSchoolClass().getId() != null) {
            SchoolClass schoolClass = schoolClassRepository.findById(studentDetails.getSchoolClass().getId())
                    .orElseThrow(() -> new RuntimeException("Class not found"));
            student.setSchoolClass(schoolClass);
        }
        
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        student.setActive(false);
        studentRepository.save(student);
    }

    public List<Student> getStudentsByClass(Long classId) {
        return studentRepository.findBySchoolClassId(classId);
    }
}

