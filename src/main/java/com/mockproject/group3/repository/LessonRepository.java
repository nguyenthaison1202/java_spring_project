package com.mockproject.group3.repository;

import com.mockproject.group3.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    Lesson findByCourseId(Integer courseId);
}
