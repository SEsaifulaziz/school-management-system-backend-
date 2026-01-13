package com.SMS.SchoolManagementSystem.exception;

import com.SMS.SchoolManagementSystem.exception.AttendanceExceptions.AttendanceNotFound;
import com.SMS.SchoolManagementSystem.exception.AttendanceExceptions.CompletedException;
import com.SMS.SchoolManagementSystem.exception.AttendanceExceptions.DroppedAttendanceException;
import com.SMS.SchoolManagementSystem.exception.AttendanceExceptions.DuplicateDateException;
import com.SMS.SchoolManagementSystem.exception.EnrollmentExceptions.*;
import com.SMS.SchoolManagementSystem.exception.StudentExceptions.DuplicateEmailException;
import com.SMS.SchoolManagementSystem.exception.StudentExceptions.StudentNotFoundException;
import com.SMS.SchoolManagementSystem.exception.SubjectExceptions.DuplicateCodeException;
import com.SMS.SchoolManagementSystem.exception.SubjectExceptions.SubjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<?> handleStudentNotFound(StudentNotFoundException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<?> handleDuplicateEmail(DuplicateEmailException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(SubjectNotFoundException.class)
        public ResponseEntity<?> handleSubjectNotFound(SubjectNotFoundException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateCodeException.class)
    public ResponseEntity<?> handleDuplicateCode(DuplicateCodeException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EnrollmentNotFoundException.class)
    public ResponseEntity<?> handleEnrollmentsNotFound(EnrollmentNotFoundException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateEnrollmentException.class)
    public ResponseEntity<?> handleDuplicateEnrollment(DuplicateEnrollmentException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AlreadyActiveException.class)
    public ResponseEntity<?> handleAlreadyActive(AlreadyActiveException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidStatusConversionException.class)
    public ResponseEntity<?> handleInvalidStatus(InvalidStatusConversionException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DroppedEnrollmentException.class)
    public ResponseEntity<?> handleDroppedStatus(DroppedEnrollmentException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncompleteSubjectException.class)
    public ResponseEntity<?> handleIncompleteSubject(IncompleteSubjectException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateGradeException.class)
    public ResponseEntity<?> handleDuplicateException(DuplicateGradeException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        for(FieldError fieldError : ex.getBindingResult().getFieldErrors()){
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(StatusNotFoundException.class)
    public ResponseEntity<?> handleStatusNotFound(StatusNotFoundException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new  ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DroppedAttendanceException.class)
    public ResponseEntity<?> handleDroppedAttendance(DroppedAttendanceException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CompletedException.class)
    public ResponseEntity<?> handleCompletedException(CompletedException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateDateException.class)
    public ResponseEntity<?> handleDuplicateDateException(DuplicateDateException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AttendanceNotFound.class)
    public ResponseEntity<?> handleAttendanceNotFound(AttendanceNotFound ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
