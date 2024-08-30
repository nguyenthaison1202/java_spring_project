package com.mockproject.group3.service;

import com.mockproject.group3.dto.EnrollmentDTO;
import com.mockproject.group3.model.Course;
import com.mockproject.group3.model.Enrollment;
import com.mockproject.group3.model.Student;
import com.mockproject.group3.repository.CourseRepository;
import com.mockproject.group3.repository.EnrollmentRepository;
import com.mockproject.group3.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Optional<Enrollment> getEnrollmentById(Integer id) {
        return enrollmentRepository.findById(id);
    }

    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public void deleteEnrollment(Integer id) {
        enrollmentRepository.deleteById(id);
    }

    public Enrollment getEnrollmentByStudentIdAndCourseId(Integer studentId, Integer courseId) {
        return enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId);
    }

    public Enrollment createEnrollment(EnrollmentDTO enrollmentDTO) {
        try {
            Student student = studentRepository.findById(enrollmentDTO.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student not found"));
            Course course = courseRepository.findById(enrollmentDTO.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Course not found"));

            Enrollment existing = enrollmentRepository.findByStudentIdAndCourseId(enrollmentDTO.getStudentId(), enrollmentDTO.getCourseId());
            if (existing != null) {
                throw new RuntimeException("Enrollment with the same studentId and courseId already exists");
            }

            Enrollment enrollment = new Enrollment();
            enrollment.setDescription(enrollmentDTO.getDescription());
            enrollment.setEnrollmentDate(LocalDateTime.now());
            enrollment.setStatus(enrollmentDTO.getStatus());
            enrollment.setStudent(student);
            enrollment.setCourse(course);

            return enrollmentRepository.save(enrollment);
        }catch (DataIntegrityViolationException e){
            throw new RuntimeException("Enrollment already exists");
        }
    }
    public Enrollment updateEnrollment(Integer id, EnrollmentDTO enrollmentDTO){
        try{
            Optional<Enrollment> existingEnrollment = enrollmentRepository.findById(id);
            if (existingEnrollment.isPresent()) {
                Enrollment enrollment = existingEnrollment.get();

                Enrollment existing = enrollmentRepository.findByStudentIdAndCourseId(enrollmentDTO.getStudentId(), enrollmentDTO.getCourseId());
                if (existing != null && existing.getId() != id) {
                    throw new RuntimeException("Enrollment with the same studentId and courseId already exists");
                }

                Student student = studentRepository.findById(enrollmentDTO.getStudentId())
                        .orElseThrow(() -> new RuntimeException("Student not found"));
                Course course = courseRepository.findById(enrollmentDTO.getCourseId())
                        .orElseThrow(() -> new RuntimeException("Course not found"));

                enrollment.setDescription(enrollmentDTO.getDescription());
                enrollment.setStatus(enrollmentDTO.getStatus());
                enrollment.setStudent(student);
                enrollment.setCourse(course);

                return enrollmentRepository.save(enrollment);
        }else{
                throw new RuntimeException("Enrollment not found");
            }
    }catch (DataIntegrityViolationException e){
        throw new RuntimeException("Enrollment already exists");
    }
    }
}
