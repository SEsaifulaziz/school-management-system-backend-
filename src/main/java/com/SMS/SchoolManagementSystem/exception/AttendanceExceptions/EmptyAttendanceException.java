package com.SMS.SchoolManagementSystem.exception.AttendanceExceptions;

import java.time.LocalDate;

public class EmptyAttendanceException extends RuntimeException {
    public EmptyAttendanceException(LocalDate date) {
        super("no attendance found for date " + date);
    }
}
