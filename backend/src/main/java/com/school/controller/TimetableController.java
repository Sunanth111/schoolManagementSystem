package com.school.controller;

import com.school.entity.Timetable;
import com.school.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timetables")
@CrossOrigin(origins = "*")
public class TimetableController {

    @Autowired
    private TimetableService timetableService;

    @PostMapping
    public Timetable createTimetable(@RequestBody Timetable timetable) {
        return timetableService.createTimetable(timetable);
    }

    @GetMapping("/class/{classId}")
    public List<Timetable> getTimetableByClass(@PathVariable Long classId) {
        return timetableService.getTimetableByClass(classId);
    }

    @GetMapping("/teacher/{teacherId}")
    public List<Timetable> getTimetableByTeacher(@PathVariable Long teacherId) {
        return timetableService.getTimetableByTeacher(teacherId);
    }
}

