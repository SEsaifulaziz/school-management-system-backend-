package com.SMS.SchoolManagementSystem.service;

import com.SMS.SchoolManagementSystem.dtos.EnrollmentDto.CreateEnrollmentRequestDto;
import com.SMS.SchoolManagementSystem.dtos.EnrollmentDto.EnrollmentResponseDto;
import com.SMS.SchoolManagementSystem.dtos.EnrollmentDto.UpdateEnrollmentRequestDto;
import com.SMS.SchoolManagementSystem.entity.Enrollment;
import com.SMS.SchoolManagementSystem.entity.EnrollmentStatusEnum;
import com.SMS.SchoolManagementSystem.entity.Student;
import com.SMS.SchoolManagementSystem.entity.Subject;
import com.SMS.SchoolManagementSystem.exception.EnrollmentExceptions.DuplicateEnrollmentException;
import com.SMS.SchoolManagementSystem.exception.EnrollmentExceptions.EnrollmentNotFoundException;
import com.SMS.SchoolManagementSystem.exception.StudentExceptions.StudentNotFoundException;
import com.SMS.SchoolManagementSystem.exception.SubjectExceptions.SubjectNotFoundException;
import com.SMS.SchoolManagementSystem.repository.EnrollmentRepository;
import com.SMS.SchoolManagementSystem.repository.StudentRepository;
import com.SMS.SchoolManagementSystem.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepo;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private SubjectRepository subjectRepo;

    public List<EnrollmentResponseDto> getAll() {
        List<Enrollment> enrollments = enrollmentRepo.findAll();
        List<EnrollmentResponseDto> responses = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            EnrollmentResponseDto response = mapToResponse(enrollment);
            responses.add(response);
        }
        return responses;
    }

    public EnrollmentResponseDto findById(Long id) {
        Enrollment enrollment = enrollmentRepo.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException(id));
        return mapToResponse(enrollment);
    }

    public EnrollmentResponseDto createEnrollment( CreateEnrollmentRequestDto request) {

        Student student = studentRepo.findById(request.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException(request.getStudentId()));

        Subject subject = subjectRepo.findById(request.getSubjectId())
                .orElseThrow(() -> new SubjectNotFoundException(request.getSubjectId()));

        boolean alreadyEnrolled = enrollmentRepo.existsByStudentAndSubject(student, subject);
        if(alreadyEnrolled)
                throw new DuplicateEnrollmentException(request.getStudentId(), request.getSubjectId());

        Enrollment enrollment = new Enrollment();

        enrollment.setStudent(student);
        enrollment.setSubject(subject);
        enrollment.setEnrollmentDate(LocalDateTime.now());
        enrollment.setStatus(EnrollmentStatusEnum.ACTIVE);

        Enrollment saved = enrollmentRepo.save(enrollment);

        return mapToResponse(saved);

    }

    public void  deleteAll(){
        enrollmentRepo.deleteAll();
    }

    public void deleteById(Long id){
        enrollmentRepo.deleteById(id);
    }


    private EnrollmentResponseDto mapToResponse(Enrollment enrollment) {

        EnrollmentResponseDto response = new EnrollmentResponseDto();

        response.setEnrollmentId(enrollment.getEnrollmentId());
        response.setStudentId(enrollment.getStudent().getId());
        response.setStudentName(enrollment.getStudent().getFirstName()
                + " " + enrollment.getStudent().getLastName());
        response.setSubjectId(enrollment.getSubject().getId());
        response.setSubjectName(enrollment.getSubject().getName());
        response.setEnrollmentDate(LocalDateTime.now());
        response.setFinalGrade(enrollment.getFinalGrade());
        response.setStatus(enrollment.getStatus());

        return response;
    }
}
