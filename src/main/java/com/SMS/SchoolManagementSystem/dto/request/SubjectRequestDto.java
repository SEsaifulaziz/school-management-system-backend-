package com.SMS.schoolmanagementsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class SubjectRequestDto {

    @NotBlank(message = "Subject Code is required")
    private String code;

    @NotBlank(message = "Subject Name is required")
    private String name;

    private String description;

    @NotBlank(message = "Grade Level is required")
    private String gradeLevel;

    @Positive
    private int creditHours;



}
