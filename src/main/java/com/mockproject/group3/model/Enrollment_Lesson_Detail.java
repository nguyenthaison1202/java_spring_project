package com.mockproject.group3.model;

import jakarta.persistence.*;

@Entity
public class Enrollment_Lesson_Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    private boolean isDone;

}
