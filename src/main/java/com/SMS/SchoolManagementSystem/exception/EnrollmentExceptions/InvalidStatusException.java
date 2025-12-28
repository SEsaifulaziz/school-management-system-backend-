package com.SMS.SchoolManagementSystem.exception.EnrollmentExceptions;

import com.SMS.SchoolManagementSystem.entity.EnrollmentStatusEnum;
import jakarta.validation.constraints.NotNull;

public class InvalidStatusException extends RuntimeException{
    public InvalidStatusException(EnrollmentStatusEnum enrollmentStatusEnum,
                                  @NotNull(message = "Enrollment status id required") EnrollmentStatusEnum updateEnrollmentRequestDto){
        super("Cannot change status from " + enrollmentStatusEnum + " status to " + updateEnrollmentRequestDto);
    }
}
