package com.SMS.SchoolManagementSystem.dtos.SubjectDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UpdateSubjectRequestDto {

    @NotBlank
    private String name;

    private String description;

    @Positive
    private int creditHours;

    private Boolean active;

}
