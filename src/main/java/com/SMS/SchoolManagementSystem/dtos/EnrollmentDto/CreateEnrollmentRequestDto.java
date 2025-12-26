package com.SMS.SchoolManagementSystem.dtos.EnrollmentDto;

import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;


@Data
@RequiredArgsConstructor
public class CreateEnrollmentRequestDto {

//    @Id
//    private Long id;

//    @Valid
    @NotBlank(message = "Student id is required")
    private Long studentId;

//    @Valid
    @NotBlank(message = "Subject id is required")
    private Long subjectId;
}
