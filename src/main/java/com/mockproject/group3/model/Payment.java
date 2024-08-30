package com.mockproject.group3.model;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mockproject.group3.enums.Status;

import jakarta.persistence.*;

@Entity
@Table(name = "Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "rating")
    private int rating;
    @Column(name = "comment")
    private String comment;
    @Column(name = "amount")
    private double amount;
    @Column(name = "payment_date")
    private LocalDateTime payment_date;

    @Enumerated(EnumType.STRING)
    Status status;

    @JsonManagedReference
    @OneToMany(mappedBy = "payment")
    private Set<PaymentDetail> paymentDetails;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Payment(int id, int rating, String comment, double amount, LocalDateTime payment_date, Status status,
            Set<PaymentDetail> paymentDetails) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.amount = amount;
        this.payment_date = payment_date;
        this.status = status;
        this.paymentDetails = paymentDetails;
    }

    public Payment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(LocalDateTime payment_date) {
        this.payment_date = payment_date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<PaymentDetail> getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(Set<PaymentDetail> paymentDetails) {
        this.paymentDetails = paymentDetails;
    }
}