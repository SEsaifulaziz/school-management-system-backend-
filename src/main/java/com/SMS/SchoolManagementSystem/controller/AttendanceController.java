package com.SMS.schoolmanagementsystem.controller;

import com.SMS.schoolmanagementsystem.dtos.AttendanceDTO.AttendanceResponseDto;
import com.SMS.schoolmanagementsystem.dtos.AttendanceDTO.CreateAttendanceRequestDto;
import com.SMS.schoolmanagementsystem.dtos.AttendanceDTO.PercentageResponseDto;
import com.SMS.schoolmanagementsystem.service.AttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/addAttendance")
    public ResponseEntity<?> addAttendance(@Valid @RequestBody CreateAttendanceRequestDto create) {
        AttendanceResponseDto saved = attendanceService.createAttendance(create);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    //without pagination
//    @GetMapping("/getAllAttendances")
//    public ResponseEntity<?> findAll(){
//        List<AttendanceResponseDto> getAll = attendanceService.getAll();
//        return new ResponseEntity<>(getAll, HttpStatus.OK);
//    }

    //with Pagination
    @GetMapping("/getAll")
    public ResponseEntity<Page<AttendanceResponseDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(attendanceService.getAll(pageable));
    }

    @GetMapping("/getAttendanceById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        AttendanceResponseDto getById = attendanceService.findById(id);
        return new ResponseEntity<>(getById, HttpStatus.OK);
    }

    @GetMapping("/getByDate/{date}")
    public ResponseEntity<?> getByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        List<AttendanceResponseDto> getByDate = attendanceService.findByDate(date);
        return new ResponseEntity<>(getByDate, HttpStatus.OK);
    }

    @GetMapping("/getAttendanceByStudentId/{id}")
    public ResponseEntity<?> getAttendanceByStudentId(@PathVariable Long id){
        List<AttendanceResponseDto> getByStudentId = attendanceService.findByStudentId(id);
        return new ResponseEntity<>(getByStudentId, HttpStatus.OK);
    }

    @GetMapping("/getAttendaceBySubjectId/{id}")
    public ResponseEntity<?> getAttendanceBySubjectId(@PathVariable Long id){
        List<AttendanceResponseDto> getBySubjectId = attendanceService.findBySubjectId(id);
        return new ResponseEntity<>(getBySubjectId, HttpStatus.OK);
    }

    @GetMapping("/getPercentageByStudent/{id}")
    public ResponseEntity<?> getPercentageByStudent(@PathVariable Long id){
        PercentageResponseDto getPercentage = attendanceService.getPercentage(id);
        return new ResponseEntity<>(getPercentage, HttpStatus.OK);
    }


    @DeleteMapping("/deleteAttendanceById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        attendanceService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/DeleteAllAttendances")
    public ResponseEntity<?> deleteAll(){
        attendanceService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

