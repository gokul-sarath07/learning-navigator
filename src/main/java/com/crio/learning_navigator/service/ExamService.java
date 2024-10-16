package com.crio.learning_navigator.service;

import com.crio.learning_navigator.entity.Exam;
import com.crio.learning_navigator.entity.Subject;

import java.util.List;

public interface ExamService {
    Exam registerForExam(Long studentId, String subjectName);
    String deleteExam(Long id);
    List<Exam> getAllExams();
    String deregisterFromExam(Long studentId, Long examId);
    Exam findExamBySubject(Subject subject);
}
