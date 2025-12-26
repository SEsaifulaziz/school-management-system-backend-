package com.SMS.SchoolManagementSystem.exception.EnrollmentExceptions;

import com.SMS.SchoolManagementSystem.entity.Student;
import com.SMS.SchoolManagementSystem.entity.Subject;

public class DuplicateEnrollmentException extends RuntimeException {
    public DuplicateEnrollmentException(Long studentId, Long subjectId) {
        super("Enrollment with student id " + studentId + " And subject id " + subjectId +  " already exists");
    }
}
