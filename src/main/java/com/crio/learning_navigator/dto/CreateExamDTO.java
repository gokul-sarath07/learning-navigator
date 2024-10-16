package com.crio.learning_navigator.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateExamDTO {
    @NotNull(message = "studentId is required")
    private Long studentId;

    @NotBlank(message = "subjectName is required")
    @Size(min = 3, message = "subjectName must have at least 3 characters")
    private String subjectName;

    public CreateExamDTO() {}

    public CreateExamDTO(Long studentId, String subjectName) {
        this.studentId = studentId;
        this.subjectName = subjectName;
    }
}
