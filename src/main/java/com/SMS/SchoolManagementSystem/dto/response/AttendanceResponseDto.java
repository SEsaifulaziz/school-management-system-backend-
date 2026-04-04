package com.SMS.schoolmanagementsystem.dto.response;

import com.SMS.schoolmanagementsystem.entity.AttendanceEnum;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;

@Data
public class AttendanceResponseDto {

    private Long attendanceId;
    private Long enrollmentId;
    private Long studentId;
    private String studentName;
    private Long subjectId;
    private String subjectName;
    private AttendanceEnum status;
    private LocalDate Date;
    private LocalTime markedAt;
}
