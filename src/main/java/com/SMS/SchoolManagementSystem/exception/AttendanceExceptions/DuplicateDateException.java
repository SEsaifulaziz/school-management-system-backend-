package com.SMS.SchoolManagementSystem.exception.AttendanceExceptions;

import java.time.LocalDate;

public class DuplicateDateException extends RuntimeException {
    public DuplicateDateException(Long id, LocalDate date) {
        super("Attendance for enrollment id " + id + " on " + date  + " is already taken");
    }
}
