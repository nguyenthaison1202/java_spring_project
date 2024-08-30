package com.mockproject.group3.controller;

import com.mockproject.group3.dto.LessonDTO;
import com.mockproject.group3.model.Enrollment;
import com.mockproject.group3.model.Lesson;
import com.mockproject.group3.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {
    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping
    public ResponseEntity<?> createLesson(@RequestBody LessonDTO lessonDTO) {
        try{
            Lesson lesson = lessonService.createLesson(lessonDTO);
            return ResponseEntity.ok(lesson);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Lesson>> getAllLessons() {
        return ResponseEntity.ok(lessonService.getAllLessons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable Integer id) {
        Optional<Lesson> lesson = lessonService.getLessonById(id);
        return lesson.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLesson(@PathVariable Integer id, @RequestBody LessonDTO lessonDTO) {
        try {
            Lesson lesson = lessonService.updateLesson(id, lessonDTO);
            return ResponseEntity.ok(lesson);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
