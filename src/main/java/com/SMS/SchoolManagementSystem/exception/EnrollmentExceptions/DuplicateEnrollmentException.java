package com.SMS.schoolmanagementsystem.exception.EnrollmentExceptions;

public class DuplicateEnrollmentException extends RuntimeException {
    public DuplicateEnrollmentException(Long studentId, Long subjectId) {
        super("Enrollment with student id " + studentId + " And subject id " + subjectId +  " already exists");
    }
}
