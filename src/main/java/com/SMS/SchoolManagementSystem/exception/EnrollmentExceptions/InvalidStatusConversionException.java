package com.SMS.schoolmanagementsystem.exception.EnrollmentExceptions;

import com.SMS.schoolmanagementsystem.entity.EnrollmentStatusEnum;
import jakarta.validation.constraints.NotNull;

public class InvalidStatusConversionException extends RuntimeException{
    public InvalidStatusConversionException(EnrollmentStatusEnum enrollmentStatusEnum,
                                            @NotNull(message = "Enrollment status id required")
                                  EnrollmentStatusEnum updateEnrollmentRequestDto){

        super("Cannot change status from " +
                enrollmentStatusEnum + " status to " +
                updateEnrollmentRequestDto);
    }
}
