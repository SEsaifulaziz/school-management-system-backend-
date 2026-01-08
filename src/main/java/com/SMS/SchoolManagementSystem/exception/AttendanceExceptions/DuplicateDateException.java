package com.SMS.SchoolManagementSystem.exception.AttendanceExceptions;

import java.time.LocalDate;

public class DuplicateDateException extends RuntimeException {
    public DuplicateDateException(LocalDate date) {
        super("Attendance for " + date + " is already taken");
    }
}
