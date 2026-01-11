package com.SMS.SchoolManagementSystem.dtos.AttendanceDTO;

import com.SMS.SchoolManagementSystem.entity.AttendenceEnum;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class CreateAttendanceRequestDto {

    @NotNull
    private Long enrollmentId;

    @NotNull
    private AttendenceEnum status;

}
