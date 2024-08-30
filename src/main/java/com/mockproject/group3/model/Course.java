package com.mockproject.group3.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

import com.mockproject.group3.enums.Status;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String description;

    private double price;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @JsonBackReference
    @OneToMany(mappedBy = "course")
    private Set<Report> reports;

    @OneToMany(mappedBy = "course")
    @JsonManagedReference
    private Set<PaymentDetail> paymentDetails;

    // @JsonManagedReference
    // @ManyToMany
    // @JoinTable(name = "payment_detail_course", joinColumns = @JoinColumn(name =
    // "course_id"), inverseJoinColumns = @JoinColumn(name = "payment_detail_id"))
    // private Set<PaymentDetail> paymentDetails;

    @JsonManagedReference
    @OneToMany(mappedBy = "course")
    private Set<Enrollment> enrollments;

    @JsonManagedReference
    @OneToMany(mappedBy = "course")
    private Set<Feedback> feedbacks;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;

    @JsonBackReference
    @OneToMany(mappedBy = "course")
    private Set<Lesson> lessons;

    public Course() {

    }

    public Course(int id, String title, String description, double price, Status status, LocalDateTime created_at,
                  LocalDateTime updated_at, Category category, Set<Report> reports, Set<PaymentDetail> paymentDetails,
                  Set<Enrollment> enrollments, Set<Feedback> feedbacks, Set<Lesson> lessons) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.category = category;
        this.reports = reports;
        this.paymentDetails = paymentDetails;
        this.enrollments = enrollments;
        this.feedbacks = feedbacks;
        this.lessons = lessons;
    }

    public Course(Course course) {
        this.id = course.id;
        this.title = course.title;
        this.description = course.description;
        this.price = course.price;
        this.status = course.status;
        this.created_at = course.created_at;
        this.updated_at = course.updated_at;
        this.category = course.category;
        this.reports = course.reports;
        this.paymentDetails = course.paymentDetails;
        this.enrollments = course.enrollments;
        this.feedbacks = course.feedbacks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Report> getReports() {
        return reports;
    }

    public void setReports(Set<Report> reports) {
        this.reports = reports;
    }

    public Set<PaymentDetail> getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(Set<PaymentDetail> paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public Set<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Set<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public Set<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Set<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }
}
