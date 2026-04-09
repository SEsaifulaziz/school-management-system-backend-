package com.SMS.schoolmanagementsystem.dto.request;

import com.SMS.schoolmanagementsystem.entity.AttendanceEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class AttendanceRequestDto {

    @NotNull(message = "Enrollment id is required")
    private Long enrollmentId;

    @NotNull(message = "Attendance status is required")
    private AttendanceEnum status;

}
