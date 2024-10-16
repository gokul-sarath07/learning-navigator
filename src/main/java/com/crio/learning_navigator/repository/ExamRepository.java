package com.crio.learning_navigator.repository;

import com.crio.learning_navigator.entity.Exam;
import com.crio.learning_navigator.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    Optional<Exam> findBySubject(Subject subject);
}
