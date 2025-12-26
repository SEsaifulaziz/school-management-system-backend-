package com.SMS.SchoolManagementSystem.exception.EnrollmentExceptions;

public class EnrollmentNotFoundException extends RuntimeException {

        public EnrollmentNotFoundException(Long id) {
        super("Enrollment with id " + id + " not found" );
    }
}
