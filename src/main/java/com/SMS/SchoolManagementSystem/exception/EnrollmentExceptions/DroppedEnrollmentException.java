package com.SMS.SchoolManagementSystem.exception.EnrollmentExceptions;

import com.SMS.SchoolManagementSystem.entity.EnrollmentStatusEnum;

public class DroppedEnrollmentException extends RuntimeException {
    public DroppedEnrollmentException(EnrollmentStatusEnum droppedStatus) {
        super("cannot add final grade to a " + droppedStatus + " subject");
    }
}
