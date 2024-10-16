package com.crio.learning_navigator.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToMany(mappedBy = "registeredExams")
    @JsonBackReference
    private List<Student> enrolledStudents = new ArrayList<>();

    public Exam() {}

    public Exam(Subject subject) {
        this.subject = subject;
    }

    public Exam(Subject subject, List<Student> students) {
        this.subject = subject;
        this.enrolledStudents = students;
    }
}
