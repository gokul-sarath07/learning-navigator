package com.crio.learning_navigator.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateSubjectDTO {

    @NotBlank(message = "name is required")
    @Size(min = 3, message = "name must have at least 3 characters")
    private String name;
}
