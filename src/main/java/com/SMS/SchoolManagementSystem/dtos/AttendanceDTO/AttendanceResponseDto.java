package com.SMS.SchoolManagementSystem.dtos.AttendanceDTO;

import com.SMS.SchoolManagementSystem.entity.AttendenceEnum;
import com.SMS.SchoolManagementSystem.entity.EnrollmentStatusEnum;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;

@Data
public class AttendanceResponseDto {

    private Long attendanceId;
    private Long studentId;
    private String studentName;
    private String subjectName;
    private AttendenceEnum status;
    private LocalDate Date;
    private LocalTime markedAt;
}
