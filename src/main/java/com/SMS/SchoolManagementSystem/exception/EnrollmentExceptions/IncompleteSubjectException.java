package com.SMS.schoolmanagementsystem.exception.EnrollmentExceptions;

import com.SMS.schoolmanagementsystem.entity.EnrollmentStatusEnum;

public class IncompleteSubjectException extends RuntimeException {
    public IncompleteSubjectException(EnrollmentStatusEnum incompleteStatus) {
        super("Subject " + incompleteStatus + " must be COMPLETED first");
    }
}
