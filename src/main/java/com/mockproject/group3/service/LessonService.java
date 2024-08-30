package com.mockproject.group3.service;

import com.mockproject.group3.dto.LessonDTO;
import com.mockproject.group3.model.Lesson;
import com.mockproject.group3.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;

    @Autowired
    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    //Create CRUD methods for Lesson
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public Optional<Lesson> getLessonById(Integer id) {
        return lessonRepository.findById(id);
    }

    public Lesson getLessonByCourseId(Integer courseId) {
        return lessonRepository.findByCourseId(courseId);
    }

    public Lesson createLesson(LessonDTO lessonDTO) {
        Lesson lesson = new Lesson();
        lesson.setTitle(lessonDTO.getTitle());
        lesson.setContent(lessonDTO.getContent());
        lesson.setCreated_at(java.time.LocalDateTime.now());
        lesson.setUpdated_at(java.time.LocalDateTime.now());
        return lessonRepository.save(lesson);
    }

    public Lesson updateLesson(Integer id, LessonDTO lessonDTO) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
        lesson.setTitle(lessonDTO.getTitle());
        lesson.setContent(lessonDTO.getContent());
        lesson.setUpdated_at(java.time.LocalDateTime.now());
        return lessonRepository.save(lesson);
    }

    public void deleteLesson(Integer id) {
        lessonRepository.deleteById(id);
    }



}
