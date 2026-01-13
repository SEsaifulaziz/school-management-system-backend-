package com.SMS.SchoolManagementSystem.exception.EnrollmentExceptions;

import com.SMS.SchoolManagementSystem.entity.EnrollmentStatusEnum;

import java.util.List;

public class StatusNotFoundException extends RuntimeException {
    public StatusNotFoundException(List<EnrollmentStatusEnum> statusEnumList) {
        super("Invalid value: allowed values are " + statusEnumList.toString());
    }
}
