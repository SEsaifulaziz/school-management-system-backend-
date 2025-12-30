package com.SMS.SchoolManagementSystem.controller;

import com.SMS.SchoolManagementSystem.dtos.StudentDto.CreateStudentRequestDto;
import com.SMS.SchoolManagementSystem.dtos.StudentDto.StudentResponseDto;
import com.SMS.SchoolManagementSystem.dtos.StudentDto.UpdateStudentRequestDto;
import com.SMS.SchoolManagementSystem.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/addStudent")
    public ResponseEntity<?> createStudent(@Valid @RequestBody CreateStudentRequestDto request){
        StudentResponseDto saved = studentService.createStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<?> getAll(){
        List<StudentResponseDto> all = studentService.getAll();
        if(!all.equals("") && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getStudentById/{id}")
    public ResponseEntity<?> findById(@Valid @PathVariable Long id){
        StudentResponseDto student = studentService.findById(id);
        if(!student.equals("") && student != null){
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteAllStudents")
    public ResponseEntity<?> deleteAll(){
        studentService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deleteStudentById/{id}")
    public ResponseEntity<?> deleteById(@Valid @PathVariable Long id){
        return new ResponseEntity<>(studentService.deleteById(id), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<?> updateStudent(@Valid @PathVariable Long id,
                                           @Valid @RequestBody UpdateStudentRequestDto updateRequest) {
        StudentResponseDto student = studentService.updateStudent(id, updateRequest);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
