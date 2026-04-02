package com.SMS.schoolmanagementsystem.exception.AttendanceExceptions;

import com.SMS.schoolmanagementsystem.entity.EnrollmentStatusEnum;

public class DroppedAttendanceException extends RuntimeException {
    public DroppedAttendanceException(EnrollmentStatusEnum  enrollmentStatus) {
        super("cannot mark a " + enrollmentStatus + " enrollment as attended");
    }
}
