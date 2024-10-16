package com.crio.learning_navigator.service;

import com.crio.learning_navigator.entity.Exam;
import com.crio.learning_navigator.entity.Student;
import com.crio.learning_navigator.entity.Subject;
import com.crio.learning_navigator.exception.DuplicateRequestException;
import com.crio.learning_navigator.exception.ExamNotFoundException;
import com.crio.learning_navigator.exception.SubjectNotFoundException;
import com.crio.learning_navigator.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private SubjectService subjectService;

    @Autowired
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    @Override
    public String deregisterFromExam(Long studentId, Long examId) {
        Exam exam = examRepository.findById(examId).orElseThrow(() -> new ExamNotFoundException("Exam with id " + examId + " not found"));
        Student student = studentService.getStudent(studentId);

        student.getRegisteredExams().remove(exam);
        exam.getEnrolledStudents().remove(student);

        studentService.saveStudent(student);
        examRepository.save(exam);

        return "Student with id " + studentId + " deregistered from " + exam.getSubject().getName() + " exam";
    }

    @Override
    public Exam findExamBySubject(Subject subject) {
        return examRepository.findBySubject(subject).orElseThrow(() -> new ExamNotFoundException(subject.getName() + " exam not found"));
    }

    @Override
    public Exam registerForExam(Long studentId, String subjectName) {
        Student student = studentService.getStudent(studentId);
        Subject subject = subjectService.findSubjectWithName(subjectName);

        boolean isEnrolled = checkIfStudentIsEnrolled(student, subject);
        if (!isEnrolled) {
            throw new SubjectNotFoundException("Student with id " + studentId + " not enrolled in subject " + subjectName);
        }

        return createExam(student, subject);
    }

    private boolean checkIfStudentIsEnrolled(Student student, Subject subject) {
        return student.getEnrolledSubjects()
                .stream()
                .anyMatch(sub -> sub.equals(subject));
    }

    private Exam createExam(Student student, Subject subject) {
        Optional<Exam> optExam = examRepository.findBySubject(subject);

        Exam exam = optExam.orElseGet(() -> new Exam(subject));

        if (exam.getEnrolledStudents().contains(student)) {
            throw new DuplicateRequestException("Student with id " + student.getId() + "already registered for " + subject.getName() + " exam");
        }

        exam.getEnrolledStudents().add(student);
        student.getRegisteredExams().add(exam);

        studentService.saveStudent(student);

        return examRepository.save(exam);
    }

    @Override
    public String deleteExam(Long id) {
        if (!examRepository.existsById(id)) {
            throw new ExamNotFoundException("Exam with id " + id + " not found");
        }
        examRepository.deleteById(id);

        return "Exam with id " + id + " has been canceled";
    }
}
