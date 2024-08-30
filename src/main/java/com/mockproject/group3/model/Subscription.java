package com.mockproject.group3.model;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "subscription")
public class Subscription {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "subscribed_at")
    private LocalDateTime subscribed_at;

    @JsonManagedReference
    @ManyToMany(mappedBy = "subscriptions")
    private Set<Instructor> instructors;

    @JsonManagedReference
    @ManyToMany(mappedBy = "subscriptions")
    private Set<Student> students;

    public Subscription(int id, LocalDateTime subscribed_at, Set<Instructor> instructors) {
        this.id = id;
        this.subscribed_at = subscribed_at;
        this.instructors = instructors;
    }

    public Subscription() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getSubscribed_at() {
        return subscribed_at;
    }

    public void setSubscribed_at(LocalDateTime subscribed_at) {
        this.subscribed_at = subscribed_at;
    }

    public Set<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(Set<Instructor> instructors) {
        this.instructors = instructors;
    }
}
