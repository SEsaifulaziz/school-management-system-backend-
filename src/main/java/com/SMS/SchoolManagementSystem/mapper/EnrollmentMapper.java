package com.SMS.schoolmanagementsystem.mapper;


import com.SMS.schoolmanagementsystem.dto.request.EnrollmentRequestDto;
import com.SMS.schoolmanagementsystem.dto.response.EnrollmentResponseDto;
import com.SMS.schoolmanagementsystem.entity.Enrollment;
import com.SMS.schoolmanagementsystem.entity.EnrollmentStatusEnum;
import com.SMS.schoolmanagementsystem.entity.Student;
import com.SMS.schoolmanagementsystem.entity.Subject;
import com.SMS.schoolmanagementsystem.exception.EnrollmentExceptions.DuplicateEnrollmentException;
import com.SMS.schoolmanagementsystem.exception.StudentExceptions.StudentNotFoundException;
import com.SMS.schoolmanagementsystem.exception.SubjectExceptions.SubjectNotFoundException;
import com.SMS.schoolmanagementsystem.repository.EnrollmentRepository;
import com.SMS.schoolmanagementsystem.repository.StudentRepository;
import com.SMS.schoolmanagementsystem.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class EnrollmentMapper {

    private final StudentRepository studentRepo;
    private final SubjectRepository subjectRepo;
    private final EnrollmentRepository enrollmentRepo;

    //RequestDto -> Entity
    public Enrollment toEntity(EnrollmentRequestDto dto){

        Student student = studentRepo.findById(dto.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException(dto.getStudentId()));

        Subject subject = subjectRepo.findById(dto.getSubjectId())
                .orElseThrow(() -> new SubjectNotFoundException(dto.getSubjectId()));

        boolean alreadyEnrolled = enrollmentRepo.existsByStudentAndSubject(student, subject);
        if (alreadyEnrolled) {
            throw new DuplicateEnrollmentException(dto.getStudentId(), dto.getSubjectId());
        }

        Enrollment enrollment = new Enrollment();

        enrollment.setStudent(student);
        enrollment.setSubject(subject);
        enrollment.setEnrollmentDate(LocalDate.now());
        enrollment.setStatus(EnrollmentStatusEnum.ACTIVE);

        return enrollment;
    }

    public EnrollmentResponseDto toResponse(Enrollment enrollment){
        EnrollmentResponseDto response = new EnrollmentResponseDto();

        response.setEnrollmentId(enrollment.getEnrollmentId());
        response.setSubjectName(enrollment.getSubject().getName());
        response.setStudentId(enrollment.getStudent().getId());
        response.setStudentName(enrollment.getStudent().getFirstName()
                + " " + enrollment.getStudent().getLastName());
        response.setSubjectId(enrollment.getSubject().getId());
        response.setSubjectName(enrollment.getSubject().getName());
        response.setEnrollmentDate(enrollment.getEnrollmentDate());
        response.setStatus(enrollment.getStatus());

        return response;
    }
}
