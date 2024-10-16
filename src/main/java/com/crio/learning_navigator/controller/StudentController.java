package com.crio.learning_navigator.controller;

import com.crio.learning_navigator.dto.CreateStudentDTO;
import com.crio.learning_navigator.dto.EnrollSubjectDTO;
import com.crio.learning_navigator.entity.Student;
import com.crio.learning_navigator.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.crio.learning_navigator.config.PathConstants.*;

@RestController
@RequestMapping(API_BASE_PATH)
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping(REGISTER_STUDENT)
    public ResponseEntity<Student> registerStudent(@Valid @RequestBody CreateStudentDTO createStudentDTO) {
        Student student = studentService.createStudent(createStudentDTO.getName());

        return ResponseEntity.ok(student);
    }

    @GetMapping(GET_ALL_STUDENTS)
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> studentList = studentService.getAllStudent();

        return ResponseEntity.ok(studentList);
    }

    @GetMapping(GET_STUDENT)
    public ResponseEntity<Student> getStudent(@PathVariable("studentId") Long studentId) {
        Student student = studentService.getStudent(studentId);

        return ResponseEntity.ok(student);
    }

    @PostMapping(ENROLL_SUBJECT)
    public ResponseEntity<String> enrollInSubject(@Valid @RequestBody EnrollSubjectDTO enrollSubjectDTO) {
        String response = studentService.addSubject(enrollSubjectDTO.getStudentId(), enrollSubjectDTO.getSubjectName());

        return ResponseEntity.ok(response);
    }

    @PostMapping(UNENROLL_SUBJECT)
    public ResponseEntity<String> unenrollInSubject(@Valid @RequestBody EnrollSubjectDTO enrollSubjectDTO) {
        String response = studentService.removeSubject(enrollSubjectDTO.getStudentId(), enrollSubjectDTO.getSubjectName());

        return ResponseEntity.ok(response);
    }
}
