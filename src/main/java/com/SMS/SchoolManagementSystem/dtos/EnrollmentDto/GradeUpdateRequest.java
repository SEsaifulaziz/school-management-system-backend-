package com.SMS.SchoolManagementSystem.dtos.EnrollmentDto;

import com.SMS.SchoolManagementSystem.entity.EnrollmentStatusEnum;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Normalized;

public class GradeUpdateRequest {

    @NotBlank(message = "Final grade id required")
    private String finalGrade;

}
