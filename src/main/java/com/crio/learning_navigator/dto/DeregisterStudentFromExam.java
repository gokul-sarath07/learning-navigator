package com.crio.learning_navigator.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeregisterStudentFromExam {
    @NotNull(message = "studentId is required")
    private Long studentId;

    @NotNull(message = "examId is required")
    private Long examId;

    public DeregisterStudentFromExam() {}

    public DeregisterStudentFromExam(Long studentId, Long examId) {
        this.examId = examId;
        this.studentId = studentId;
    }
}
