package com.mockproject.group3.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "payment_detail")
public class PaymentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // @JsonManagedReference
    // @ManyToMany(mappedBy = "paymentDetails")
    // Set<Course> courses;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference
    private Course course;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    public PaymentDetail(int id, Course course, Payment payment) {
        this.id = id;
        this.course = course;
        this.payment = payment;
    }

    public PaymentDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourses() {
        return course;
    }

    public void setCourses(Course course) {
        this.course = course;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
