package com.SMS.schoolmanagementsystem.dtos.EnrollmentDto;

import com.SMS.schoolmanagementsystem.entity.EnrollmentStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UpdateEnrollmentRequestDto {

    @NotNull(message = "Enrollment status is required")
    private EnrollmentStatusEnum status;

}
