package com.SMS.SchoolManagementSystem.exception.SubjectExceptions;

public class SubjectNotFoundException extends RuntimeException {
    public SubjectNotFoundException(Long id){
        super("Subject with id " + id + " not found");
    }
}
