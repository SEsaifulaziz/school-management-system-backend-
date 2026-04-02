package com.SMS.schoolmanagementsystem.dtos.SubjectDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UpdateSubjectRequestDto {

    @NotBlank(message = "Subject Name is required")
    private String name;

    private String description;

    @Positive
    private int creditHours;

    private Boolean active;
}
