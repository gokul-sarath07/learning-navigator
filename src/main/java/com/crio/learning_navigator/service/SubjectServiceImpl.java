package com.crio.learning_navigator.service;

import com.crio.learning_navigator.entity.Subject;
import com.crio.learning_navigator.exception.SubjectNotFoundException;
import com.crio.learning_navigator.repository.SubjectRepository;
import com.crio.learning_navigator.exception.DuplicateRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Subject addSubject(String name) {
        if (subjectRepository.existsByName(name)) {
            throw new DuplicateRequestException("Subject with name " + name + " already exists");
        }
        Subject subject = new Subject(name);

        return subjectRepository.save(subject);
    }

    @Override
    public String deleteSubject(Long id) {
        if (!subjectRepository.existsById(id)) {
            throw new SubjectNotFoundException("Subject with id " + id + " not found");
        }
        subjectRepository.deleteById(id);

        return "Subject with id " + id + " has been deleted";
    }

    @Override
    public Subject findSubjectWithName(String subjectName) {
        return subjectRepository.findByName(subjectName)
                .orElseThrow(
                        () -> new SubjectNotFoundException("Subject with name " + subjectName + " not found"));
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject findSubjectWithId(Long id) {
        return subjectRepository.findById(id).orElseThrow(
                () -> new SubjectNotFoundException("Subject with id " + id + " not found"));
    }

    @Override
    public String saveSubject(Subject subject) {
        Long id = subjectRepository.save(subject).getId();

        return "Subject with id " + id + " has been saved";
    }
}
