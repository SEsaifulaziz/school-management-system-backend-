package com.SMS.SchoolManagementSystem.controller;

import com.SMS.SchoolManagementSystem.dtos.SubjectDto.CreateSubjectRequestDto;
import com.SMS.SchoolManagementSystem.dtos.SubjectDto.SubjectResponseDto;
import com.SMS.SchoolManagementSystem.dtos.SubjectDto.UpdateSubjectRequestDto;
import com.SMS.SchoolManagementSystem.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping("/addSubject")
    public ResponseEntity<?> createSubject(@Valid @RequestBody CreateSubjectRequestDto request){

        SubjectResponseDto saved = subjectService.createSubject(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/getAllSubjects")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(subjectService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getSubjectById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){

        return new ResponseEntity<>(subjectService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/updateSubject/{id}")
    public ResponseEntity<?> updateSubject(@Valid @PathVariable Long id,
                                           @Valid @RequestBody UpdateSubjectRequestDto updateSubjectRequest){
        SubjectResponseDto update = subjectService.updateStudentRequestDto(id, updateSubjectRequest);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAllSubjects")
    public ResponseEntity<?> deleteAll() {
        subjectService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deleteSubjectById/{id}")
    public ResponseEntity<?> deleteById(@Valid @PathVariable Long id){
        subjectService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
