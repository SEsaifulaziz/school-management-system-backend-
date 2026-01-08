package com.SMS.SchoolManagementSystem.exception.AttendanceExceptions;

import com.SMS.SchoolManagementSystem.entity.EnrollmentStatusEnum;

public class CompletedException extends RuntimeException {
    public CompletedException(EnrollmentStatusEnum enrollmentStatus) {
        super("cannot mark a " + enrollmentStatus + " enrollment as attended");
    }
}
