package com.SMS.schoolmanagementsystem.exception.StudentExceptions;

public class DuplicateEmailException extends RuntimeException{
    public DuplicateEmailException(String email){
        super("Student with email " + email + " already exists");
    }
}
