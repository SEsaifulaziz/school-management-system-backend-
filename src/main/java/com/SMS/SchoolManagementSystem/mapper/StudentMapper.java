package com.SMS.schoolmanagementsystem.mapper;

import com.SMS.schoolmanagementsystem.dto.request.StudentRequestDto;
import com.SMS.schoolmanagementsystem.dto.response.StudentResponseDto;
import com.SMS.schoolmanagementsystem.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    //Request
    // DTO -> Entity
    public Student toEntity(StudentRequestDto dto){
        Student student = new Student();

        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setEnrolledGrade(dto.getEnrolledGrade());
        return student;
    }

    //Entity -> ResponseDto
    public StudentResponseDto toResponse(Student student){
        StudentResponseDto response = new StudentResponseDto();

        response.setId(student.getId());
        response.setFullName(student.getFirstName() + " " + student.getLastName());
        response.setEmail(student.getEmail());
        response.setEnrolledGrade(student.getEnrolledGrade());
        return response;
    }
}