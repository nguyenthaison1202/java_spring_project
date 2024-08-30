package com.mockproject.group3.dto;

import java.time.LocalDateTime;

import com.mockproject.group3.enums.EnrollmentStatus;

public class EnrollmentDTO {
    private String description;
    private EnrollmentStatus status;
    private int studentId;
    private int courseId;

    public EnrollmentDTO() {
    }

    public EnrollmentDTO(String description, EnrollmentStatus status, int studentId, int courseId) {
        this.description = description;
        this.status = status;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    // Getters and Setters

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
