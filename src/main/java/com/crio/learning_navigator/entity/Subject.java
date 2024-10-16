package com.crio.learning_navigator.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "enrolledSubjects")
    @JsonBackReference
    private List<Student> registeredStudents = new ArrayList<>();

    public Subject() {}

    public Subject(String name) {
        this.name = name;
    }

    public Subject(String name, List<Student> registeredStudents) {
        this.name = name;
        this.registeredStudents = registeredStudents;
    }
}
