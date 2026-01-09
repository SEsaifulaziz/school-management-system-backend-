package com.SMS.SchoolManagementSystem.service;

import com.SMS.SchoolManagementSystem.dtos.AttendanceDTO.AttendanceResponseDto;
import com.SMS.SchoolManagementSystem.dtos.AttendanceDTO.CreateAttendanceRequestDto;
import com.SMS.SchoolManagementSystem.entity.Attendance;
import com.SMS.SchoolManagementSystem.entity.AttendenceEnum;
import com.SMS.SchoolManagementSystem.entity.Enrollment;
import com.SMS.SchoolManagementSystem.entity.EnrollmentStatusEnum;
import com.SMS.SchoolManagementSystem.exception.AttendanceExceptions.*;
import com.SMS.SchoolManagementSystem.exception.EnrollmentExceptions.EnrollmentNotFoundException;
import com.SMS.SchoolManagementSystem.exception.EnrollmentExceptions.UnActiveEnrollmentException;
import com.SMS.SchoolManagementSystem.repository.AttendanceRepository;
import com.SMS.SchoolManagementSystem.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepo;
    private final EnrollmentRepository enrollmentRepo;

    public List<AttendanceResponseDto> findAllAttendance() {
        List<Attendance> attendances = attendanceRepo.findAll();
        List<AttendanceResponseDto> responses = new ArrayList<>();

        for(Attendance attendance : attendances) {
            AttendanceResponseDto attendanceResponse = mapToResponse(attendance);
            responses.add(attendanceResponse);
        }
        return responses;
    }

    public AttendanceResponseDto findById(Long id) {
        Attendance attendance = attendanceRepo.findById(id)
                .orElseThrow(() -> new AttendanceNotFound(id));
        return mapToResponse(attendance);
    }

    public List<AttendanceResponseDto> findByDate(LocalDate date) {
        List<Attendance> attendance = attendanceRepo.findAllByAttendanceDate(date);

        List<AttendanceResponseDto> responses = new ArrayList<>();

        for(Attendance attendances : attendance){

            AttendanceResponseDto attendanceResponse = mapToResponse(attendances);

//            if(!attendanceResponse.equals(" ") && attendanceResponse != null) {
//                responses.add(attendanceResponse);
//            }
//            throw new EmptyAttendanceException(date);

        }
        return responses;
    }

    public AttendanceResponseDto createAttendance(CreateAttendanceRequestDto request) {

        Enrollment enrollment = enrollmentRepo.findById(request.getEnrollmentId())
                .orElseThrow(() -> new EnrollmentNotFoundException(request.getEnrollmentId()));


        if(enrollment.getStatus() == EnrollmentStatusEnum.COMPLETED){
            if(enrollment.getStatus() != EnrollmentStatusEnum.ACTIVE){
                if(enrollment.getStatus() == EnrollmentStatusEnum.DROPPED){
                    throw new DroppedAttendanceException(enrollment.getStatus());
                }
                throw new UnActiveEnrollmentException(enrollment.getStatus());
            }
            throw new CompletedException(enrollment.getStatus());
        }

        if(attendanceRepo.existsByAttendanceDateAndEnrollment(LocalDate.now(), enrollment)) {
            throw new DuplicateDateException(request.getEnrollmentId(), LocalDate.now());
        }

        Attendance attendance = new Attendance();
        attendance.setEnrollment(enrollment);
        attendance.setAttendanceDate(LocalDate.now());
        attendance.setMarkedAt(LocalTime.now());
        attendance.setStatus(request.getStatus());
        Attendance saved  = attendanceRepo.save(attendance);

        return mapToResponse(saved);
    }


    public void deleteById(Long id){
        attendanceRepo.deleteById(id);
    }
    
    private AttendanceResponseDto mapToResponse(Attendance attendance) {

        AttendanceResponseDto response = new AttendanceResponseDto();

        response.setAttendanceId(attendance.getId());
        response.setEnrollmentId(attendance.getEnrollment().getEnrollmentId());
        response.setStudentId(attendance.getEnrollment().getStudent().getId());
        response.setStudentName(attendance.getEnrollment().getStudent().getFirstName()
                + " " + attendance.getEnrollment().getStudent().getLastName() );
        response.setSubjectName(attendance.getEnrollment().getSubject().getName());
        response.setStatus(attendance.getStatus());
        response.setDate(attendance.getAttendanceDate());
        response.setMarkedAt(attendance.getMarkedAt());

        return response;
    }
}
