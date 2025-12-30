package com.SMS.SchoolManagementSystem.service;

import com.SMS.SchoolManagementSystem.dtos.EnrollmentDto.CreateEnrollmentRequestDto;
import com.SMS.SchoolManagementSystem.dtos.EnrollmentDto.EnrollmentResponseDto;
import com.SMS.SchoolManagementSystem.dtos.EnrollmentDto.GradeUpdateRequestDto;
import com.SMS.SchoolManagementSystem.dtos.EnrollmentDto.UpdateEnrollmentRequestDto;
import com.SMS.SchoolManagementSystem.entity.Enrollment;
import com.SMS.SchoolManagementSystem.entity.EnrollmentStatusEnum;
import com.SMS.SchoolManagementSystem.entity.Student;
import com.SMS.SchoolManagementSystem.entity.Subject;
import com.SMS.SchoolManagementSystem.exception.EnrollmentExceptions.*;
import com.SMS.SchoolManagementSystem.exception.StudentExceptions.StudentNotFoundException;
import com.SMS.SchoolManagementSystem.exception.SubjectExceptions.SubjectNotFoundException;
import com.SMS.SchoolManagementSystem.repository.EnrollmentRepository;
import com.SMS.SchoolManagementSystem.repository.StudentRepository;
import com.SMS.SchoolManagementSystem.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.management.LockInfo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.SMS.SchoolManagementSystem.entity.EnrollmentStatusEnum.*;

@Service
@RequiredArgsConstructor
public class EnrollmentService {


    private final EnrollmentRepository enrollmentRepo;
    private final StudentRepository studentRepo;
    private final SubjectRepository subjectRepo;


    public List<EnrollmentResponseDto> getAll() {
        List<Enrollment> enrollments = enrollmentRepo.findAll();
        List<EnrollmentResponseDto> responses = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            EnrollmentResponseDto response = mapToResponse(enrollment);
            responses.add(response);
        }
        return responses;
    }

    public List<EnrollmentResponseDto> getEnrollmentsByStudentId(Long studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));

        List<Enrollment> enrollments = enrollmentRepo.findByStudent(student);

        List<EnrollmentResponseDto> responses = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            EnrollmentResponseDto responseDto = mapToResponse(enrollment);
            responses.add(responseDto);
        }
        return responses;
    }

    public List<EnrollmentResponseDto> getEnrollmentsBySubjectId(Long subjectId) {
        Subject subject = subjectRepo.findById(subjectId)
                .orElseThrow(() -> new SubjectNotFoundException(subjectId));

        List<Enrollment> enrollments = enrollmentRepo.findBySubject(subject);

        List<EnrollmentResponseDto> responses = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            EnrollmentResponseDto responseDto = mapToResponse(enrollment);
            responses.add(responseDto);
        }
        return responses;
    }

    public List<EnrollmentResponseDto> getActiveEnrollmentsByStudentId(Long id){
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        List<Enrollment> enrollments = enrollmentRepo.findByStudentAndStatus(student, ACTIVE);

        List<EnrollmentResponseDto> responses = new ArrayList<>();

        for(Enrollment enrollment: enrollments){
            EnrollmentResponseDto responseDto = mapToResponse(enrollment);
            responses.add(responseDto);
        }
        return responses;
    }

    public List<EnrollmentResponseDto> getCompletedEnrollmentsByStudent(Long id){
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        List<Enrollment> enrollments = enrollmentRepo.findByStudentAndStatus(student, COMPLETED);

        List<EnrollmentResponseDto> responses = new ArrayList<>();

        for(Enrollment enrollment: enrollments) {
            EnrollmentResponseDto responseDto = mapToResponse(enrollment);
            responses.add(responseDto);
        }
        return responses;
    }

    public List<EnrollmentResponseDto> getDroppedEnrollmentsByStudent(Long id){
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        List<Enrollment> enrollments = enrollmentRepo.findByStudentAndStatus(student, DROPPED);

        List<EnrollmentResponseDto> responses = new ArrayList<>();

        for(Enrollment enrollment: enrollments){
            EnrollmentResponseDto responseDto = mapToResponse(enrollment);
            responses.add(responseDto);
        }
        return responses;
    }

    public EnrollmentResponseDto findById(Long id) {
        Enrollment enrollment = enrollmentRepo.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException(id));
        return mapToResponse(enrollment);
    }

    public EnrollmentResponseDto createEnrollment(CreateEnrollmentRequestDto request) {

        Student student = studentRepo.findById(request.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException(request.getStudentId()));

        Subject subject = subjectRepo.findById(request.getSubjectId())
                .orElseThrow(() -> new SubjectNotFoundException(request.getSubjectId()));

        boolean alreadyEnrolled = enrollmentRepo.existsByStudentAndSubject(student, subject);
        if (alreadyEnrolled) {
            throw new DuplicateEnrollmentException(request.getStudentId(), request.getSubjectId());
        }

        Enrollment enrollment = new Enrollment();

        enrollment.setStudent(student);
        enrollment.setSubject(subject);
        enrollment.setEnrollmentDate(LocalDate.now());
        enrollment.setStatus(ACTIVE);

        Enrollment saved = enrollmentRepo.save(enrollment);

        return mapToResponse(saved);

    }

    public EnrollmentResponseDto updateEnrollmentStatus(Long id, UpdateEnrollmentRequestDto updateEnrollmentRequestDto ) {

        Enrollment enrollment = enrollmentRepo.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException(id));

        if (enrollment.getStatus() == updateEnrollmentRequestDto.getStatus()) {
            throw new AlreadyActiveException(updateEnrollmentRequestDto.getStatus());
        }

        if (enrollment.getStatus() == EnrollmentStatusEnum.DROPPED ||
                enrollment.getStatus() == COMPLETED) {
            throw new InvalidStatusException(enrollment.getStatus(), updateEnrollmentRequestDto.getStatus());
        }

        enrollment.setStatus(updateEnrollmentRequestDto.getStatus());
        Enrollment updatedStatus = enrollmentRepo.save(enrollment);

        return mapToResponse(updatedStatus);
    }

    public EnrollmentResponseDto updateGradeStatus(Long id, GradeUpdateRequestDto updateRequestDto) {
        Enrollment enrollment = enrollmentRepo.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException(id));

        if (enrollment.getFinalGrade() != null) {
            throw new DuplicateGradeException(id, enrollment.getFinalGrade());
        }

        if (enrollment.getStatus() == ACTIVE) {
            throw new IncompleteSubjectException(enrollment.getStatus());
        }

        if (enrollment.getStatus() == EnrollmentStatusEnum.DROPPED) {
            throw new DroppedEnrollmentException(enrollment.getStatus());
        }

        enrollment.setFinalGrade(updateRequestDto.getFinalGrade());
        Enrollment updated = enrollmentRepo.save(enrollment);

        return mapToResponse(updated);

    }

    public void deleteAll() {
        enrollmentRepo.deleteAll();
    }

    public void deleteById(Long id) {
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
        response.setEnrollmentDate(enrollment.getEnrollmentDate());
        response.setFinalGrade(enrollment.getFinalGrade());
        response.setStatus(enrollment.getStatus());

        return response;
    }
}
