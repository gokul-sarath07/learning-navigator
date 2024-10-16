package com.crio.learning_navigator.service;

import com.crio.learning_navigator.entity.Exam;
import com.crio.learning_navigator.entity.Student;
import com.crio.learning_navigator.entity.Subject;
import com.crio.learning_navigator.exception.ExamNotFoundException;
import com.crio.learning_navigator.exception.StudentNotFoundException;
import com.crio.learning_navigator.repository.StudentRepository;
import com.crio.learning_navigator.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ExamService examService;

    @Override
    public Student createStudent(String name) {
        Student student = new Student(name);

        return studentRepository.save(student);
    }

    @Override
    public Student getStudent(Long id) {
        return getStudentWithId(id);
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public String addSubject(Long studentId, String subjectName) {
        Student student = getStudentWithId(studentId);
        Subject subject = subjectService.findSubjectWithName(subjectName);

        if (!student.getEnrolledSubjects().contains(subject)) {
            student.getEnrolledSubjects().add(subject);
            this.saveStudent(student);
        }
        if (!subject.getRegisteredStudents().contains(student)) {
            subject.getRegisteredStudents().add(student);
            subjectService.saveSubject(subject);
        }

        return "Student with Id " + studentId + " enrolled for subject " + subjectName;
    }

    @Override
    public String removeSubject(Long studentId, String subjectName) {
        Student student = getStudentWithId(studentId);
        Subject subject = subjectService.findSubjectWithName(subjectName);

        if (student.getEnrolledSubjects().contains(subject)) {
            student.getEnrolledSubjects().remove(subject);
            this.saveStudent(student);
        }

        if (subject.getRegisteredStudents().contains(student)) {
            subject.getRegisteredStudents().remove(student);
            subjectService.saveSubject(subject);
        }
        deregisterStudentFromExam(student, subject);

        return "Student with Id " + studentId + " unenrolled from subject " + subjectName;
    }

    private void deregisterStudentFromExam(Student student, Subject subject) {
        try {
            Exam exam = examService.findExamBySubject(subject);
            examService.deregisterFromExam(student.getId(), exam.getId());
        } catch (ExamNotFoundException ex) {
            // do nothing
        }
    }

    @Override
    public String deleteStudentWithId(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Student with id " + id + " not found");
        }

        studentRepository.deleteById(id);

        return "Student with Id " + id + " removed";
    }

    @Override
    public String saveStudent(Student student) {
        Long id = studentRepository.save(student).getId();

        return "Student with id " + id + " has been saved";
    }

    private Student getStudentWithId(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(
                        () -> new StudentNotFoundException("Student with id " + studentId + " not found"));
    }
}
