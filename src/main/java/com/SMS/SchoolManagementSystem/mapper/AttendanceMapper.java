package com.SMS.schoolmanagementsystem.mapper;

import com.SMS.schoolmanagementsystem.dto.request.AttendanceRequestDto;
import com.SMS.schoolmanagementsystem.dto.request.EnrollmentRequestDto;
import com.SMS.schoolmanagementsystem.dto.response.AttendanceResponseDto;
import com.SMS.schoolmanagementsystem.entity.Attendance;
import com.SMS.schoolmanagementsystem.entity.Enrollment;
import com.SMS.schoolmanagementsystem.entity.EnrollmentStatusEnum;
import com.SMS.schoolmanagementsystem.exception.AttendanceExceptions.CompletedException;
import com.SMS.schoolmanagementsystem.exception.AttendanceExceptions.DroppedAttendanceException;
import com.SMS.schoolmanagementsystem.exception.AttendanceExceptions.DuplicateDateException;
import com.SMS.schoolmanagementsystem.exception.EnrollmentExceptions.EnrollmentNotFoundException;
import com.SMS.schoolmanagementsystem.repository.AttendanceRepository;
import com.SMS.schoolmanagementsystem.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class AttendanceMapper {

    private final EnrollmentRepository enrollmentRepo;
    private final AttendanceRepository attendanceRepo;

    public Attendance toEntity(AttendanceRequestDto dto){

        Enrollment enrollment = enrollmentRepo.findById(dto.getEnrollmentId())
                .orElseThrow(() -> new EnrollmentNotFoundException(dto.getEnrollmentId()));

        if(enrollment.getStatus() == EnrollmentStatusEnum.COMPLETED){
            throw new CompletedException(enrollment.getStatus());
        }

        if(enrollment.getStatus() == EnrollmentStatusEnum.DROPPED) {
            throw new DroppedAttendanceException(enrollment.getStatus());
        }

        if(attendanceRepo.existsByAttendanceDateAndEnrollment(LocalDate.now(), enrollment)) {
            throw new DuplicateDateException(dto.getEnrollmentId(), LocalDate.now());
        }

        Attendance attendance = new Attendance();
        attendance.setEnrollment(enrollment);
        attendance.setAttendanceDate(LocalDate.now());
        attendance.setStatus(dto.getStatus());
        attendance.setMarkedAt(LocalTime.now());

        return attendanceRepo.save(attendance);
    }

    public AttendanceResponseDto toResponse(Attendance attendance){
        AttendanceResponseDto response = new AttendanceResponseDto();

        response.setAttendanceId(attendance.getId());
        response.setEnrollmentId(attendance.getEnrollment().getEnrollmentId());
        response.setStudentId(attendance.getEnrollment().getStudent().getId());
        response.setStudentName(attendance.getEnrollment().getStudent().getFirstName()
                + " " + attendance.getEnrollment().getStudent().getLastName());
        response.setSubjectId(attendance.getEnrollment().getSubject().getId());
        response.setSubjectName(attendance.getEnrollment().getSubject().getName());
        response.setStatus(attendance.getStatus());
        response.setDate(attendance.getAttendanceDate());
        response.setMarkedAt(attendance.getMarkedAt());

        return response;

    }
}
