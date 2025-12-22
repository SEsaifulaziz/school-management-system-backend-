package com.SMS.SchoolManagementSystem.service;

import com.SMS.SchoolManagementSystem.dtos.StudentDto.CreateStudentRequestDto;
import com.SMS.SchoolManagementSystem.dtos.StudentDto.StudentResponseDto;
import com.SMS.SchoolManagementSystem.dtos.StudentDto.UpdateStudentRequestDto;
import com.SMS.SchoolManagementSystem.entity.Student;
import com.SMS.SchoolManagementSystem.exception.DuplicateEmailException;
import com.SMS.SchoolManagementSystem.exception.StudentNotFoundException;
import com.SMS.SchoolManagementSystem.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    @Autowired
    private StudentRepository studentRepo;

    public List<StudentResponseDto> getAll(){
        List<Student> students = studentRepo.findAll();
        List<StudentResponseDto> responses = new ArrayList<>();

        // Simple forLoop
//        for(Student student: students){
//            responses.add(mapToResponse(student));
//        }

        for(Student student: students){
            StudentResponseDto response = mapToResponse(student);
            responses.add(response);
        }
        return responses;
    }

    public StudentResponseDto findById(Long id){
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return mapToResponse(student);
    }

    public StudentResponseDto addStudent(CreateStudentRequestDto req){
        if(studentRepo.existsByEmail(req.getEmail()))
            throw new DuplicateEmailException(req.getEmail());

        Student student = new Student();
        student.setFirstName(req.getFirstName());
        student.setLastName(req.getLastName());
        student.setEmail(req.getEmail());
        student.setEnrolledGrade(req.getEnrolledGrade());

        Student saved =  studentRepo.save(student);

        return mapToResponse(saved);
    }

    public Student deleteById(Long id){
        studentRepo.deleteById(id);
        return null;
    }

    public void deleteAll(){
        studentRepo.deleteAll();
    }

    public StudentResponseDto updateStudent(Long id, UpdateStudentRequestDto updateRequest){
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        // check email conflict ONLY if email is changed
        if(!student.getEmail().equals(updateRequest.getEmail())){
            if(studentRepo.existsByEmail(updateRequest.getEmail())){
                throw new DuplicateEmailException(updateRequest.getEmail());
            }
        }
        student.setFirstName(updateRequest.getFirstName());
        student.setLastName(updateRequest.getLastName());
        student.setEmail(updateRequest.getEmail());
        student.setEnrolledGrade(updateRequest.getEnrolledGrade());

        Student updatedStudent = studentRepo.save(student);

        return mapToResponse(updatedStudent);
    }

    private StudentResponseDto mapToResponse (Student student){
        StudentResponseDto response = new StudentResponseDto();
        response.setId(student.getId());
        response.setFirstName(student.getFirstName());
        response.setLastName(student.getLastName());
        response.setEmail(student.getEmail());
        response.setEnrolledGrade(student.getEnrolledGrade());
        return response;
    }













}
