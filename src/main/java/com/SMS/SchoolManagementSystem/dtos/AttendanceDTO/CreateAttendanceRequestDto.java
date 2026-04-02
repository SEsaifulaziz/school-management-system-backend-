package com.SMS.schoolmanagementsystem.dtos.AttendanceDTO;

import com.SMS.schoolmanagementsystem.entity.AttendanceEnum;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class CreateAttendanceRequestDto {

    @NotNull(message = "Enrollment id is required")
    private Long enrollmentId;

    @NotNull(message = "Attendance status is required")
    private AttendanceEnum status;

}
