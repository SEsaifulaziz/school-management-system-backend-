package com.SMS.SchoolManagementSystem.exception.EnrollmentExceptions;

import com.SMS.SchoolManagementSystem.entity.EnrollmentStatusEnum;

public class UnActiveEnrollmentException extends RuntimeException {
    public UnActiveEnrollmentException(EnrollmentStatusEnum unActiveEnrollmentStatusEnum) {
        super("Enrollment must be ACTIVE ");
    }
}
