package com.mockproject.group3.repository;

import com.mockproject.group3.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

    Enrollment findByStudentIdAndCourseId(Integer studentId, Integer courseId);
}
