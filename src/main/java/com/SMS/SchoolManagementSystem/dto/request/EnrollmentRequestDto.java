package com.SMS.schoolmanagementsystem.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class EnrollmentRequestDto {

    @NotNull(message = "Student id is required")
    private Long studentId;

    @NotNull(message = "Student id is required")
    private Long subjectId;
}
