package com.SMS.SchoolManagementSystem.controller;

import com.SMS.SchoolManagementSystem.dtos.AttendanceDTO.AttendanceResponseDto;
import com.SMS.SchoolManagementSystem.dtos.AttendanceDTO.CreateAttendanceRequestDto;
import com.SMS.SchoolManagementSystem.entity.Attendance;
import com.SMS.SchoolManagementSystem.service.AttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerTypePredicate;
import org.w3c.dom.stylesheets.LinkStyle;

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
        List<AttendanceResponseDto> getAll = attendanceService.getAllAttendance();
        return new ResponseEntity<>(getAll, HttpStatus.OK);
    }

    @GetMapping("/getAttendanceById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        AttendanceResponseDto getById = attendanceService.findById(id);
        return new ResponseEntity<>(getById, HttpStatus.OK);
    }
}
