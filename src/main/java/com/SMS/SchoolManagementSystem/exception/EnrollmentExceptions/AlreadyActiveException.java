package com.SMS.schoolmanagementsystem.exception.EnrollmentExceptions;

import com.SMS.schoolmanagementsystem.entity.EnrollmentStatusEnum;

public class AlreadyActiveException extends RuntimeException {
    public AlreadyActiveException(EnrollmentStatusEnum enrollmentStatusEnum) {
        super("Enrollment already in " + enrollmentStatusEnum + " status");
    }
}
