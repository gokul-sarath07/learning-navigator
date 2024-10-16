package com.crio.learning_navigator.controller;


import com.crio.learning_navigator.dto.CreateExamDTO;
import com.crio.learning_navigator.dto.DeregisterStudentFromExam;
import com.crio.learning_navigator.entity.Exam;
import com.crio.learning_navigator.service.ExamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.crio.learning_navigator.config.PathConstants.*;

@RestController
@RequestMapping(API_BASE_PATH)
public class ExamController {

    @Autowired
    private ExamService examService;

    @PostMapping(REGISTER_EXAM)
    public ResponseEntity<Exam> registerStudent(@Valid @RequestBody CreateExamDTO createExamDTO) {
        Exam exam = examService.registerForExam(createExamDTO.getStudentId(), createExamDTO.getSubjectName());

        return ResponseEntity.ok(exam);
    }

    @PostMapping(DEREGISTER_EXAM)
    public ResponseEntity<String> deregisterStudent(@Valid @RequestBody DeregisterStudentFromExam deregisterStudent) {
        String response = examService.deregisterFromExam(deregisterStudent.getStudentId(), deregisterStudent.getExamId());

        return ResponseEntity.ok(response);
    }

    @GetMapping(GET_ALL_EXAMS)
    public ResponseEntity<List<Exam>> getAllRegisteredExams() {
        List<Exam> examList = examService.getAllExams();

        return ResponseEntity.ok(examList);
    }
}
