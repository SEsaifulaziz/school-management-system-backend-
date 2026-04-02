package com.SMS.schoolmanagementsystem.exception.AttendanceExceptions;

public class AttendanceNotFound extends RuntimeException {
    public AttendanceNotFound(Long id) {
        super("Attendance with id " + id + " not found");
    }
}
