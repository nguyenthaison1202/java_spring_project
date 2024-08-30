package com.mockproject.group3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mockproject.group3.dto.EnrollmentDTO;
import com.mockproject.group3.model.Course;
import com.mockproject.group3.model.Enrollment;
import com.mockproject.group3.model.Student;
import com.mockproject.group3.service.EnrollmentService;
import com.mockproject.group3.repository.StudentRepository;
import com.mockproject.group3.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.enrollmentService = enrollmentService;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @PostMapping
    public ResponseEntity<?> createEnrollment(@RequestBody EnrollmentDTO enrollmentDTO) {
        try {
            Enrollment enrollment = enrollmentService.createEnrollment(enrollmentDTO);
            return ResponseEntity.ok(enrollment);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Integer id) {
        Optional<Enrollment> enrollment = enrollmentService.getEnrollmentById(id);
        return enrollment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Enrollment> getEnrollmentByStudentIdAndCourseId(@PathVariable Integer studentId, @PathVariable Integer courseId) {
        Enrollment enrollment = enrollmentService.getEnrollmentByStudentIdAndCourseId(studentId, courseId);
        if (enrollment != null) {
            return ResponseEntity.ok(enrollment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEnrollment(@PathVariable Integer id, @RequestBody EnrollmentDTO enrollmentDTO) {
        try {
            Enrollment updatedEnrollment = enrollmentService.updateEnrollment(id, enrollmentDTO);
            return ResponseEntity.ok(updatedEnrollment);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Integer id) {
        Optional<Enrollment> enrollment = enrollmentService.getEnrollmentById(id);
        if (enrollment.isPresent()) {
            enrollmentService.deleteEnrollment(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
