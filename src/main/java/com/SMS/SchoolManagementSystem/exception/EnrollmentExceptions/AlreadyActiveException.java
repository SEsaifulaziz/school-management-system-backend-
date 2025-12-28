package com.SMS.SchoolManagementSystem.exception.EnrollmentExceptions;

import com.SMS.SchoolManagementSystem.entity.EnrollmentStatusEnum;

public class AlreadyActiveException extends RuntimeException {
    public AlreadyActiveException(EnrollmentStatusEnum enrollmentStatusEnum) {
        super("Enrollment already in " + enrollmentStatusEnum + " status");
    }
}
