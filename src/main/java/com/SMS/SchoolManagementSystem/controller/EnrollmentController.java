package com.SMS.schoolmanagementsystem.controller;


import com.SMS.schoolmanagementsystem.dto.request.EnrollmentRequestDto;
import com.SMS.schoolmanagementsystem.dto.response.EnrollmentResponseDto;
import com.SMS.schoolmanagementsystem.dto.request.GradeUpdateRequestDto;
import com.SMS.schoolmanagementsystem.dto.request.UpdateEnrollmentRequestDto;
import com.SMS.schoolmanagementsystem.entity.EnrollmentStatusEnum;
import com.SMS.schoolmanagementsystem.service.EnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<?> createEnrollment(@Valid @RequestBody EnrollmentRequestDto request){
        EnrollmentResponseDto saved = enrollmentService.createEnrollment(request);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

       //without Pagination
//    @GetMapping("/getAllEnrollments")
//    public ResponseEntity<?> findAll(){
//        List<EnrollmentResponseDto> getAll = enrollmentService.getAll();
//        return new ResponseEntity<>(getAll, HttpStatus.OK);
//    }

    //with Pagination
    @GetMapping("enrollments")
    public ResponseEntity<Page<EnrollmentResponseDto>> getEnrollments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(enrollmentService.getEnrollments(pageable));

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
