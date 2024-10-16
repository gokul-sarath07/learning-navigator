package com.crio.learning_navigator.service;

import com.crio.learning_navigator.entity.Student;
import com.crio.learning_navigator.entity.Subject;
import com.crio.learning_navigator.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private SubjectService subjectService;

    @Mock
    private ExamService examService;

    @Test
    void testCreateStudent_ShouldCreateStudentSuccessfully() {
        String studentName = "John Doe";
        Student student = new Student(studentName);
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student createdStudent = studentService.createStudent(studentName);

        assertNotNull(createdStudent);
        assertEquals(studentName, createdStudent.getName());
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void testGetStudent_ShouldReturnStudent() {
        Long studentId = 1L;
        Student student = new Student("John Doe");
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        Student foundStudent = studentService.getStudent(studentId);

        assertNotNull(foundStudent);
        assertEquals("John Doe", foundStudent.getName());
        verify(studentRepository, times(1)).findById(studentId);
    }

    @Test
    void testAddSubject_ShouldEnrollStudentInSubject() {
        Long studentId = 1L;
        String subjectName = "Math";
        Student student = new Student("John Doe");
        Subject subject = new Subject(subjectName);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(subjectService.findSubjectWithName(subjectName)).thenReturn(subject);
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(subjectService.saveSubject(any(Subject.class))).thenReturn("Subject saved");

        String result = studentService.addSubject(studentId, subjectName);

        assertEquals("Student with Id 1 enrolled for subject Math", result);
        verify(studentRepository, times(1)).findById(studentId);
        verify(subjectService, times(1)).findSubjectWithName(subjectName);
        verify(studentRepository, times(1)).save(any(Student.class));
        verify(subjectService, times(1)).saveSubject(any(Subject.class));
    }
}