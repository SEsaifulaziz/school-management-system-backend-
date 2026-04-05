package com.SMS.schoolmanagementsystem.mapper;

import com.SMS.schoolmanagementsystem.dto.request.SubjectRequestDto;
import com.SMS.schoolmanagementsystem.dto.response.SubjectResponseDto;
import com.SMS.schoolmanagementsystem.entity.Subject;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapper {

    //DTO -> Entity
    public Subject toEntity(SubjectRequestDto dto){
        Subject subject = new Subject();

        subject.setName(dto.getName());
        subject.setDescription(dto.getDescription());
        subject.setCode(dto.getCode());
        subject.setGradeLevel(dto.getGradeLevel());
        subject.setCreditHours(dto.getCreditHours());
        return subject;
    }

    //Entity -> ResponseDto
    public SubjectResponseDto toResponse(Subject subject){
        SubjectResponseDto response = new SubjectResponseDto();

        response.setId(subject.getId());
        response.setName(subject.getName());
        response.setDescription(subject.getDescription());
        response.setCode(subject.getCode());
        response.setGradeLevel(subject.getGradeLevel());
        response.setCreditHours(subject.getCreditHours());
        response.setActive(subject.getActive());
        return response;
    }
}
