package com.crio.learning_navigator.service;

import com.crio.learning_navigator.entity.Subject;
import com.crio.learning_navigator.exception.DuplicateRequestException;
import com.crio.learning_navigator.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubjectServiceImplTest {

    @InjectMocks
    private SubjectServiceImpl subjectService;

    @Mock
    private SubjectRepository subjectRepository;

    @Test
    void testAddSubject_ShouldAddSubjectSuccessfully() {
        String subjectName = "Science";
        Subject subject = new Subject(subjectName);
        when(subjectRepository.existsByName(subjectName)).thenReturn(false);
        when(subjectRepository.save(any(Subject.class))).thenReturn(subject);

        Subject savedSubject = subjectService.addSubject(subjectName);

        assertNotNull(savedSubject);
        assertEquals("Science", savedSubject.getName());
        verify(subjectRepository, times(1)).existsByName(subjectName);
        verify(subjectRepository, times(1)).save(any(Subject.class));
    }

    @Test
    void testAddSubject_ShouldThrowDuplicateRequestException() {
        String subjectName = "Math";
        when(subjectRepository.existsByName(subjectName)).thenReturn(true);

        DuplicateRequestException exception = assertThrows(DuplicateRequestException.class,
                () -> subjectService.addSubject(subjectName));
        assertEquals("Subject with name Math already exists", exception.getMessage());
        verify(subjectRepository, times(1)).existsByName(subjectName);
        verify(subjectRepository, never()).save(any(Subject.class));
    }

    @Test
    void testFindSubjectWithName_ShouldReturnSubject() {
        String subjectName = "Math";
        Subject subject = new Subject(subjectName);
        when(subjectRepository.findByName(subjectName)).thenReturn(Optional.of(subject));

        Subject foundSubject = subjectService.findSubjectWithName(subjectName);

        assertNotNull(foundSubject);
        assertEquals("Math", foundSubject.getName());
        verify(subjectRepository, times(1)).findByName(subjectName);
    }
}