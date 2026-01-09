package com.SMS.SchoolManagementSystem.dtos.AttendanceDTO;

import com.SMS.SchoolManagementSystem.entity.AttendenceEnum;
import jakarta.persistence.Column;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Data
public class CreateAttendanceRequestDto {

    @NotNull
    private Long enrollmentId;

    @NotNull
    private AttendenceEnum status;

    @NotNull
    @Column(unique = true)
    private LocalDate attendanceDate;


}
