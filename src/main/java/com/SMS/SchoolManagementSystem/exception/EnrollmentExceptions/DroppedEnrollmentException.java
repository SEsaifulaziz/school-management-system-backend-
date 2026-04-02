package com.SMS.schoolmanagementsystem.exception.EnrollmentExceptions;

import com.SMS.schoolmanagementsystem.entity.EnrollmentStatusEnum;

public class DroppedEnrollmentException extends RuntimeException {
    public DroppedEnrollmentException(EnrollmentStatusEnum droppedStatus) {
        super("cannot add final grade to a " + droppedStatus + " subject");
    }
}
