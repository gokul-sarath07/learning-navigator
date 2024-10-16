package com.crio.learning_navigator.service;

import com.crio.learning_navigator.entity.Subject;

import java.util.List;

public interface SubjectService {
    Subject addSubject(String name);
    String deleteSubject(Long id);
    Subject findSubjectWithName(String name);
    List<Subject> getAllSubjects();
    Subject findSubjectWithId(Long id);
    String saveSubject(Subject subject);
}
