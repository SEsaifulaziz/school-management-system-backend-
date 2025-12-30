package com.SMS.SchoolManagementSystem.dtos.EnrollmentDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GradeUpdateRequestDto {

    @NotBlank(message = "Final grade is required")
    private String finalGrade;


}
