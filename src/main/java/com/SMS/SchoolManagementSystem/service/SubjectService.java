package com.SMS.SchoolManagementSystem.service;

import com.SMS.SchoolManagementSystem.dtos.SubjectDto.CreateSubjectRequestDto;
import com.SMS.SchoolManagementSystem.dtos.SubjectDto.SubjectResponseDto;
import com.SMS.SchoolManagementSystem.dtos.SubjectDto.UpdateSubjectRequestDto;
import com.SMS.SchoolManagementSystem.entity.Subject;
import com.SMS.SchoolManagementSystem.exception.SubjectExceptions.DuplicateCodeException;
import com.SMS.SchoolManagementSystem.exception.SubjectExceptions.SubjectNotFoundException;
import com.SMS.SchoolManagementSystem.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepo;


    public List<SubjectResponseDto> getAll(){

        List<Subject> subjects = subjectRepo.findAll();
        List<SubjectResponseDto> responses = new ArrayList<>();

        for(Subject subject : subjects) {
            SubjectResponseDto response = mapToResponse(subject);
            responses.add(response);
        }
        return responses;
    }

    public SubjectResponseDto findById(Long id){

        Subject subject = subjectRepo.findById(id)
                .orElseThrow(()-> new SubjectNotFoundException(id));

        return mapToResponse(subject);
    }

    public SubjectResponseDto createSubject(CreateSubjectRequestDto req){

        if(subjectRepo.existsByCode(req.getCode()))
            throw new DuplicateCodeException(req.getCode());

        Subject subject = new Subject();
        subject.setCode(req.getCode());
        subject.setName(req.getName());
        subject.setDescription(req.getDescription());
        subject.setGradeLevel(req.getGradeLevel());
        subject.setCreditHours(req.getCreditHours());

        Subject saved = subjectRepo.save(subject);

        return mapToResponse(saved);

    }

    public void deleteById(Long id){

        subjectRepo.deleteById(id);
    }

    public void deleteAll(){

        subjectRepo.deleteAll();
    }

    public SubjectResponseDto updateStudentRequestDto(Long id, UpdateSubjectRequestDto updateRequest){

        Subject subject = subjectRepo.findById(id)
                .orElseThrow(() -> new SubjectNotFoundException(id));

        subject.setName(updateRequest.getName());
        subject.setDescription(updateRequest.getDescription());
        subject.setCreditHours(updateRequest.getCreditHours());
        subject.setActive(updateRequest.getActive());

        Subject updated = subjectRepo.save(subject);

        return mapToResponse(updated);
    }

    private SubjectResponseDto mapToResponse(Subject subject) {
        SubjectResponseDto response = new SubjectResponseDto();
        response.setId(subject.getId());
        response.setCode(subject.getCode());
        response.setName(subject.getName());
        response.setGradeLevel(subject.getGradeLevel());
        response.setCreditHours(subject.getCreditHours());
        response.setDescription(subject.getDescription());
        response.setActive(subject.getActive());

        return response;

    }






}
