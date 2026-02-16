package com.school.service;

import com.school.entity.SchoolClass;
import com.school.repository.SchoolClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService {

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    public List<SchoolClass> getAllClasses() {
        return schoolClassRepository.findByActiveTrue();
    }

    public Optional<SchoolClass> getClassById(Long id) {
        return schoolClassRepository.findById(id);
    }

    public SchoolClass createClass(SchoolClass schoolClass) {
        return schoolClassRepository.save(schoolClass);
    }

    public SchoolClass updateClass(Long id, SchoolClass classDetails) {
        SchoolClass schoolClass = schoolClassRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class not found"));
        
        schoolClass.setClassName(classDetails.getClassName());
        schoolClass.setSection(classDetails.getSection());
        schoolClass.setCapacity(classDetails.getCapacity());
        schoolClass.setRoomNumber(classDetails.getRoomNumber());
        
        return schoolClassRepository.save(schoolClass);
    }

    public void deleteClass(Long id) {
        SchoolClass schoolClass = schoolClassRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class not found"));
        schoolClass.setActive(false);
        schoolClassRepository.save(schoolClass);
    }
}

