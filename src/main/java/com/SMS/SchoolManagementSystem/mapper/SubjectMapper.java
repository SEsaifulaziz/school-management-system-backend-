package com.SMS.schoolmanagementsystem.mapper;

import com.SMS.schoolmanagementsystem.dto.request.SubjectRequestDto;
import com.SMS.schoolmanagementsystem.entity.Subject;
import org.springframework.stereotype.Component;

@Component
public class ClassMapper {

    public Subject toEntity(SubjectRequestDto dto){
        Subject subject = new Subject();

        subject.setName(dto.getName());
        subject.setDescription(dto.getDescription());
    }
}
