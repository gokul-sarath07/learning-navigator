package com.crio.learning_navigator.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateStudentDTO {

    @NotBlank(message = "name is required")
    @Size(min = 5, message = "name must have at least 5 characters")
    private String name;
}
