package com.SMS.schoolmanagementsystem.exception.EnrollmentExceptions;

import com.SMS.schoolmanagementsystem.entity.EnrollmentStatusEnum;

import java.util.List;

public class StatusNotFoundException extends RuntimeException {
    public StatusNotFoundException(List<EnrollmentStatusEnum> statusEnumList) {
        super("Invalid value: allowed values are " + statusEnumList.toString());
    }
}
