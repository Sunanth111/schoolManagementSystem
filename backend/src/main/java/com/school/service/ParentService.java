package com.school.service;

import com.school.entity.Parent;
import com.school.entity.Student;
import com.school.repository.ParentRepository;
import com.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParentService {

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private StudentRepository studentRepository;

    public List<Parent> getAllParents() {
        return parentRepository.findByActiveTrue();
    }

    public Optional<Parent> getParentById(Long id) {
        return parentRepository.findById(id);
    }

    public Parent createParent(Parent parent) {
        if (parent.getParentId() == null || parent.getParentId().isEmpty()) {
            parent.setParentId("PRT" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        }
        return parentRepository.save(parent);
    }

    public Parent updateParent(Long id, Parent parentDetails) {
        Parent parent = parentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parent not found"));

        parent.setFirstName(parentDetails.getFirstName());
        parent.setLastName(parentDetails.getLastName());
        parent.setEmail(parentDetails.getEmail());
        parent.setPhone(parentDetails.getPhone());
        parent.setAlternatePhone(parentDetails.getAlternatePhone());
        parent.setAddress(parentDetails.getAddress());
        parent.setOccupation(parentDetails.getOccupation());
        parent.setRelationship(parentDetails.getRelationship());

        if (parentDetails.getStudents() != null) {
            parent.setStudents(parentDetails.getStudents());
        }

        return parentRepository.save(parent);
    }

    public Parent linkStudentToParent(Long parentId, Long studentId) {
        Parent parent = parentRepository.findById(parentId)
                .orElseThrow(() -> new RuntimeException("Parent not found"));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (!parent.getStudents().contains(student)) {
            parent.getStudents().add(student);
        }

        return parentRepository.save(parent);
    }

    public void deleteParent(Long id) {
        Parent parent = parentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parent not found"));
        parent.setActive(false);
        parentRepository.save(parent);
    }
}

