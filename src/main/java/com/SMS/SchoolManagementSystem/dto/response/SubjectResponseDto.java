package com.SMS.schoolmanagementsystem.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SubjectResponseDto {

    private Long id;
    private String code;
    private String name;
    private String description;
    private String gradeLevel;
    private int creditHours;
    private Boolean active;
}
