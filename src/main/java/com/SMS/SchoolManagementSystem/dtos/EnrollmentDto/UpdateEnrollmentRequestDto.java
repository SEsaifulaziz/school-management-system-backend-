package com.SMS.SchoolManagementSystem.dtos.EnrollmentDto;

import com.SMS.SchoolManagementSystem.entity.EnrollmentStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UpdateEnrollmentRequestDto {

    @NotNull(message = "Enrollment status id required")
    private EnrollmentStatusEnum status;

}
