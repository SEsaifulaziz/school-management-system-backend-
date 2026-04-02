package com.SMS.schoolmanagementsystem.exception.AttendanceExceptions;

import com.SMS.schoolmanagementsystem.entity.EnrollmentStatusEnum;

public class CompletedException extends RuntimeException {
    public CompletedException(EnrollmentStatusEnum enrollmentStatus) {
        super("cannot mark a " + enrollmentStatus + " enrollment as attended");
    }
}
