package com.SMS.SchoolManagementSystem.controller;


import com.SMS.SchoolManagementSystem.dtos.EnrollmentDto.CreateEnrollmentRequestDto;
import com.SMS.SchoolManagementSystem.dtos.EnrollmentDto.EnrollmentResponseDto;
import com.SMS.SchoolManagementSystem.dtos.EnrollmentDto.GradeUpdateRequestDto;
import com.SMS.SchoolManagementSystem.dtos.EnrollmentDto.UpdateEnrollmentRequestDto;
import com.SMS.SchoolManagementSystem.entity.EnrollmentStatusEnum;
import com.SMS.SchoolManagementSystem.service.EnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;


    @PostMapping("/addEnrollment")
    public ResponseEntity<?> createEnrollment(@Valid @RequestBody CreateEnrollmentRequestDto request){
        EnrollmentResponseDto saved = enrollmentService.createEnrollment(request);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/getAllEnrollments")
    public ResponseEntity<?> findAll(){
        List<EnrollmentResponseDto> getAll = enrollmentService.getAll();
        return new ResponseEntity<>(getAll, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        EnrollmentResponseDto findById = enrollmentService.findById(id);
        return new ResponseEntity<>(findById, HttpStatus.OK);
    }

    @GetMapping("/getByStudentId/{id}")
    public ResponseEntity<?> getByStudentId(@PathVariable Long id){
        List<EnrollmentResponseDto> getBySId = enrollmentService.getEnrollmentsByStudentId(id);
        return new ResponseEntity<>(getBySId, HttpStatus.OK);
    }

    @GetMapping("/getBySubjectId/{id}")
    public ResponseEntity<?> getBySubjectId(@PathVariable Long id){
        List<EnrollmentResponseDto> getById = enrollmentService.getEnrollmentsBySubjectId(id);
        return new ResponseEntity<>(getById, HttpStatus.OK);
    }

    @GetMapping("/getByStudentAndStatus/{id}/status/{status}")
    public ResponseEntity<?> getByStudentAndStatus(@PathVariable Long id, @PathVariable EnrollmentStatusEnum status){
        List<EnrollmentResponseDto> getByStudentAndStatus = enrollmentService.getByStudentAndStatus(id, status);
        return new ResponseEntity<>(getByStudentAndStatus, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAllEnrollments")
    public ResponseEntity<?> deleteAll(){
        enrollmentService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        enrollmentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("updateStatus/{id}")
    public ResponseEntity<?> updateStatus(@Valid @PathVariable Long id,
                                    @Valid @RequestBody UpdateEnrollmentRequestDto updateEnrollmentRequestDto){
        EnrollmentResponseDto updated = enrollmentService.updateEnrollmentStatus(id, updateEnrollmentRequestDto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @PutMapping("updateGrade/{id}")
    public ResponseEntity<?> updateGrade(@PathVariable Long id,
                                         @Valid @RequestBody GradeUpdateRequestDto gradeUpdateRequestDto){
        EnrollmentResponseDto updateGrade = enrollmentService.updateGradeStatus(id, gradeUpdateRequestDto);
        return new ResponseEntity<>(updateGrade, HttpStatus.OK);
    }













}
