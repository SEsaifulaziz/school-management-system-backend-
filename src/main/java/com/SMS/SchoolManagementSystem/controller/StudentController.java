package com.SMS.schoolmanagementsystem.controller;

import com.SMS.schoolmanagementsystem.dto.request.StudentRequestDto;
import com.SMS.schoolmanagementsystem.dto.response.StudentResponseDto;
import com.SMS.schoolmanagementsystem.dto.request.UpdateStudentRequestDto;
import com.SMS.schoolmanagementsystem.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/addStudent")
    public ResponseEntity<?> createStudent(@Valid @RequestBody StudentRequestDto request){
        StudentResponseDto saved = studentService.createStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
             //without Pagination
//    @GetMapping("/getAllStudents")
//    public ResponseEntity<?> getAll(){
//        List<StudentResponseDto> all = studentService.getAll();
//        if(!all.equals("") && !all.isEmpty()){
//            return new ResponseEntity<>(all, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

            //with Pagination
//    @GetMapping("/students")
//    public ResponseEntity<Page<StudentResponseDto>> getAll(Pageable pageable){
//        Page<StudentResponseDto> page = studentService.getStudents(pageable);
//
//        if(page.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT)
//        }
//
//        return new ResponseEntity<>(page, HttpStatus.OK);
//    }

         //even cleaner
    @GetMapping("/students")
    public ResponseEntity<Page<StudentResponseDto>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(studentService.getAll(pageable));
    }


    @GetMapping("/getStudentById/{id}")
    public ResponseEntity<?> findById(@Valid @PathVariable Long id){
        StudentResponseDto student = studentService.findById(id);
        if(student != null){
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAll(){
        studentService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deleteStudentById/{id}")
    public ResponseEntity<?> deleteById(@Valid @PathVariable Long id){
        return new ResponseEntity<>(studentService.deleteById(id), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(@Valid @PathVariable Long id,
                                           @Valid @RequestBody UpdateStudentRequestDto updateRequest) {
        StudentResponseDto student = studentService.updateStudent(id, updateRequest);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
