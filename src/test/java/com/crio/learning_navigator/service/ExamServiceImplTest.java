package com.crio.learning_navigator.service;

import com.crio.learning_navigator.entity.Exam;
import com.crio.learning_navigator.entity.Student;
import com.crio.learning_navigator.entity.Subject;
import com.crio.learning_navigator.repository.ExamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExamServiceImplTest {

    @InjectMocks
    private ExamServiceImpl examService;

    @Mock
    private ExamRepository examRepository;

    @Mock
    private StudentService studentService;

    @Mock
    private SubjectService subjectService;

    @Test
    void testGetAllExams_ShouldReturnListOfExams() {
        List<Exam> exams = new ArrayList<>();
        exams.add(new Exam(new Subject("Math")));
        exams.add(new Exam(new Subject("Science")));
        when(examRepository.findAll()).thenReturn(exams);

        List<Exam> allExams = examService.getAllExams();

        assertNotNull(allExams);
        assertEquals(2, allExams.size());
        verify(examRepository, times(1)).findAll();
    }

    @Test
    void testDeregisterFromExam_ShouldDeregisterStudentFromExam() {
        Long studentId = 1L;
        Long examId = 1L;
        Exam exam = new Exam(new Subject("Math"));
        Student student = new Student("John Doe");

        when(examRepository.findById(examId)).thenReturn(Optional.of(exam));
        when(studentService.getStudent(studentId)).thenReturn(student);

        String result = examService.deregisterFromExam(studentId, examId);

        assertEquals("Student with id 1 deregistered from Math exam", result);
        verify(examRepository, times(1)).findById(examId);
        verify(studentService, times(1)).getStudent(studentId);
        verify(examRepository, times(1)).save(exam);
        verify(studentService, times(1)).saveStudent(student);
    }
}