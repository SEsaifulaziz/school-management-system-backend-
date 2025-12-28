package com.SMS.SchoolManagementSystem.controller;


import com.SMS.SchoolManagementSystem.dtos.EnrollmentDto.CreateEnrollmentRequestDto;
import com.SMS.SchoolManagementSystem.dtos.EnrollmentDto.EnrollmentResponseDto;
import com.SMS.SchoolManagementSystem.dtos.EnrollmentDto.GradeUpdateRequestDto;
import com.SMS.SchoolManagementSystem.dtos.EnrollmentDto.UpdateEnrollmentRequestDto;
import com.SMS.SchoolManagementSystem.service.EnrollmentService;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;


    @PostMapping
    public ResponseEntity<?> createEnrollment(@Valid @RequestBody CreateEnrollmentRequestDto request){
        EnrollmentResponseDto saved = enrollmentService.createEnrollment(request);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<EnrollmentResponseDto> getAll = enrollmentService.getAll();
        return new ResponseEntity<>(getAll, HttpStatus.OK);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        EnrollmentResponseDto findById = enrollmentService.findById(id);
        return new ResponseEntity<>(findById, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll(){
        enrollmentService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteById(Long id){
        enrollmentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<?> updateStatus(@Valid @PathVariable Long id,
                                    @Valid @RequestBody UpdateEnrollmentRequestDto updateEnrollmentRequestDto){
        EnrollmentResponseDto updated = enrollmentService.updateEnrollmentStatus(id, updateEnrollmentRequestDto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @PutMapping("uid/{id}")
    public ResponseEntity<?> updateGrade(@PathVariable Long id,
                                         @Valid @RequestBody GradeUpdateRequestDto gradeUpdateRequestDto){
        EnrollmentResponseDto updateGrade = enrollmentService.updateGradeStatus(id, gradeUpdateRequestDto);
        return new ResponseEntity<>(updateGrade, HttpStatus.OK);
    }













}
