package com.SMS.SchoolManagementSystem.exception.AttendanceExceptions;

import com.SMS.SchoolManagementSystem.entity.EnrollmentStatusEnum;

public class DroppedAttendanceException extends RuntimeException {
    public DroppedAttendanceException(EnrollmentStatusEnum  enrollmentStatus) {
        super("cannot mark a " + enrollmentStatus + " enrollment as attended");
    }
}
