package com.SMS.schoolmanagementsystem.controller;

import com.SMS.schoolmanagementsystem.dto.request.SubjectRequestDto;
import com.SMS.schoolmanagementsystem.dto.response.SubjectResponseDto;
import com.SMS.schoolmanagementsystem.dto.request.UpdateSubjectRequestDto;
import com.SMS.schoolmanagementsystem.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping("/addSubject")
    public ResponseEntity<?> createSubject(@Valid @RequestBody SubjectRequestDto request){

        SubjectResponseDto saved = subjectService.createSubject(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    //without pagination
//    @GetMapping("/getAllSubjects")
//    public ResponseEntity<?> getAll(){
//        return new ResponseEntity<>(subjectService.getAll(), HttpStatus.OK);
//    }

    @GetMapping("/subjects")
    public ResponseEntity<Page<SubjectResponseDto>> getAllSubjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
      Pageable pageable = PageRequest.of(page, size);
      return ResponseEntity.ok(subjectService.getSubjects(pageable));
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
