package com.SMS.schoolmanagementsystem.dtos.EnrollmentDto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class CreateEnrollmentRequestDto {

    @NotNull(message = "Student id is required")
    private Long studentId;

    @NotNull(message = "Student id is required")
    private Long subjectId;
}
