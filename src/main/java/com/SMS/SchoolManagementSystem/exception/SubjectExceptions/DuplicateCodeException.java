package com.SMS.schoolmanagementsystem.exception.SubjectExceptions;

public class DuplicateCodeException extends RuntimeException {
    public DuplicateCodeException(String code){
        super("Subject with code " + code + " already exists");
    }
}
