package com.SMS.schoolmanagementsystem.dtos.StudentDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateStudentRequestDto {

    @NotBlank(message = "First Name is required")
    private String firstName;

    @NotBlank(message = "Last Name is required")
    private String lastName;

    @Email
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "EnrolledGrade is required")
    private String enrolledGrade;
}
