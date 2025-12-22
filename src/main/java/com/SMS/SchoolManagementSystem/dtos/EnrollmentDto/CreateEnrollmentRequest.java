package com.SMS.SchoolManagementSystem.dtos.EnrollmentDto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateEnrollmentRequest {

    @NotBlank(message = "Student id is required")
    private Long studentId;

    @NotBlank(message = "Subject id is required")
    private Long subjectId;
}
