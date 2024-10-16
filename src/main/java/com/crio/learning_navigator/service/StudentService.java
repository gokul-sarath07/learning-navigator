package com.crio.learning_navigator.service;


import com.crio.learning_navigator.entity.Student;

import java.util.List;

public interface StudentService {
    Student createStudent(String name);
    List<Student> getAllStudent();
    Student getStudent(Long id);
    String addSubject(Long studentId, String subjectName);
    String removeSubject(Long studentId, String subjectName);
    String deleteStudentWithId(Long id);
    String saveStudent(Student student);
}
