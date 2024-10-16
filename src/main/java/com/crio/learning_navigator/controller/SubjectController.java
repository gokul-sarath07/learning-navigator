package com.crio.learning_navigator.controller;

import com.crio.learning_navigator.dto.CreateSubjectDTO;
import com.crio.learning_navigator.entity.Subject;
import com.crio.learning_navigator.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.crio.learning_navigator.config.PathConstants.*;

@RestController
@RequestMapping(API_BASE_PATH)
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping(ADD_SUBJECT)
    public ResponseEntity<Subject> addSubject(@Valid @RequestBody CreateSubjectDTO createSubjectDTO) {
        Subject subject = subjectService.addSubject(createSubjectDTO.getName());

        return ResponseEntity.ok(subject);
    }

    @DeleteMapping(DELETE_SUBJECT)
    public ResponseEntity<String> deleteSubject(@PathVariable("subjectId") Long subjectId) {
        String response = subjectService.deleteSubject(subjectId);

        return ResponseEntity.ok(response);
    }

    @GetMapping(GET_ALL_SUBJECTS)
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> subjectList = subjectService.getAllSubjects();

        return ResponseEntity.ok(subjectList);
    }

    @GetMapping(GET_SUBJECT)
    public ResponseEntity<Subject> getSubject(@PathVariable("subjectId") Long subjectId) {
        Subject subject = subjectService.findSubjectWithId(subjectId);

        return ResponseEntity.ok(subject);
    }
}
