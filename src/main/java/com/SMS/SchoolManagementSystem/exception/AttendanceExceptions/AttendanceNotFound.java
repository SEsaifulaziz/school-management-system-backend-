package com.SMS.SchoolManagementSystem.exception.AttendanceExceptions;

public class AttendanceNotFound extends RuntimeException {
    public AttendanceNotFound(Long id) {
        super("Attendance with id " + id + " not found");
    }
}
