package com.SMS.SchoolManagementSystem.dtos.EnrollmentDto;

import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;


@Data
@RequiredArgsConstructor
public class CreateEnrollmentRequestDto {

    @NotNull(message = "Student id is required")
    private Long studentId;

    @NotNull(message = "Student id is required")
    private Long subjectId;
}
