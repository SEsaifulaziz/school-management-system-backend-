package com.SMS.SchoolManagementSystem.exception.EnrollmentExceptions;

import com.SMS.SchoolManagementSystem.entity.EnrollmentStatusEnum;

public class IncompleteSubjectException extends RuntimeException {
    public IncompleteSubjectException(EnrollmentStatusEnum incompleteStatus) {
        super("Subject " + incompleteStatus + " must be COMPLETED first");
    }
}
