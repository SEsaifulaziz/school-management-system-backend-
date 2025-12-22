package com.SMS.SchoolManagementSystem.dtos.EnrollmentDto;

import com.SMS.SchoolManagementSystem.entity.EnrollmentStatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EnrollmentResponse {

    private Long enrollmentId;

    private Long studentId;
    private String studentName;

    private Long subjectId;
    private String subjectName;

    private LocalDateTime enrollmentDate;
    private EnrollmentStatusEnum status;
    private String finalGrade;

}
