package com.SMS.schoolmanagementsystem.service;

import com.SMS.schoolmanagementsystem.dtos.SubjectDto.CreateSubjectRequestDto;
import com.SMS.schoolmanagementsystem.dtos.SubjectDto.SubjectResponseDto;
import com.SMS.schoolmanagementsystem.dtos.SubjectDto.UpdateSubjectRequestDto;
import com.SMS.schoolmanagementsystem.entity.Subject;
import com.SMS.schoolmanagementsystem.exception.SubjectExceptions.DuplicateCodeException;
import com.SMS.schoolmanagementsystem.exception.SubjectExceptions.SubjectNotFoundException;
import com.SMS.schoolmanagementsystem.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepo;

           //without pagination
//    public List<SubjectResponseDto> getAll() {
//
//        List<Subject> subjects = subjectRepo.findAll();
//        List<SubjectResponseDto> responses = new ArrayList<>();
//
//        for (Subject subject : subjects) {
//            SubjectResponseDto response = mapToResponse(subject);
//            responses.add(response);
//        }
//        return responses;
//    }

           //with pagination
    public Page<SubjectResponseDto> getSubjects(Pageable pageable) {
        Page<Subject> subjectPage = subjectRepo.findAll(pageable);
        return subjectPage.map(this::mapToResponse);
    }

    public SubjectResponseDto findById(Long id) {

        Subject subject = subjectRepo.findById(id)
                .orElseThrow(() -> new SubjectNotFoundException(id));

        return mapToResponse(subject);
    }

    public SubjectResponseDto createSubject(CreateSubjectRequestDto req) {

        if (subjectRepo.existsByCode(req.getCode()))
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

    public void deleteById(Long id) {
        subjectRepo.deleteById(id);
    }

    public void deleteAll() {

        subjectRepo.deleteAll();
    }

    public SubjectResponseDto updateStudentRequestDto(Long id,
                                                      UpdateSubjectRequestDto
                                                              updateRequest) {

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
