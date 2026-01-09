package com.SMS.SchoolManagementSystem.controller;

import com.SMS.SchoolManagementSystem.dtos.AttendanceDTO.AttendanceResponseDto;
import com.SMS.SchoolManagementSystem.dtos.AttendanceDTO.CreateAttendanceRequestDto;
import com.SMS.SchoolManagementSystem.entity.Attendance;
import com.SMS.SchoolManagementSystem.service.AttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerTypePredicate;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/addAttendance")
    public ResponseEntity<?> addAttendance(@Valid @RequestBody CreateAttendanceRequestDto create) {
        AttendanceResponseDto saved  =attendanceService.createAttendance(create);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/getAllAttendances")
    public ResponseEntity<?> findAll(){
        List<AttendanceResponseDto> getAll = attendanceService.findAllAttendance();
        return new ResponseEntity<>(getAll, HttpStatus.OK);
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

