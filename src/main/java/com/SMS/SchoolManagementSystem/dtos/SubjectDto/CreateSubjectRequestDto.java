package com.SMS.SchoolManagementSystem.dtos.SubjectDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateSubjectRequestDto {

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private String gradeLevel;

    @Positive
    private int creditHours;



}
