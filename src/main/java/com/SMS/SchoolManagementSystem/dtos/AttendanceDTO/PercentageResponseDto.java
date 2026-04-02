package com.SMS.schoolmanagementsystem.dtos.AttendanceDTO;

import lombok.Data;

@Data
public class PercentageResponseDto {
    private long studentId;
    private String studentName;
    private long totalClasses;
    private long present = 0;
    private double percentage;
}
