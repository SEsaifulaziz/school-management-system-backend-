package com.SMS.SchoolManagementSystem.exception.EnrollmentExceptions;

public class DuplicateGradeException extends RuntimeException {
    public DuplicateGradeException(Long id, String finalGrade ) {
        super("student " + id + " already have " + finalGrade + " FinalGrade");
    }
}
