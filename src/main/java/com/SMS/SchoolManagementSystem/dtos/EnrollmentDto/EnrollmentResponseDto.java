package com.SMS.SchoolManagementSystem.dtos.EnrollmentDto;

import com.SMS.SchoolManagementSystem.entity.EnrollmentStatusEnum;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class EnrollmentResponseDto {

    private Long enrollmentId;

    private Long studentId;
    private String studentName;

    private Long subjectId;
    private String subjectName;

    private LocalDate enrollmentDate;
    private EnrollmentStatusEnum status;
    private String finalGrade;

}
