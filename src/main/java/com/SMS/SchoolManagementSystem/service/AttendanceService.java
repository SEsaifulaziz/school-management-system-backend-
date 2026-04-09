package com.SMS.schoolmanagementsystem.service;

import com.SMS.schoolmanagementsystem.dto.response.AttendanceResponseDto;
import com.SMS.schoolmanagementsystem.dto.request.AttendanceRequestDto;
import com.SMS.schoolmanagementsystem.dto.response.PercentageResponseDto;
import com.SMS.schoolmanagementsystem.entity.*;
import com.SMS.schoolmanagementsystem.exception.AttendanceExceptions.*;
import com.SMS.schoolmanagementsystem.exception.StudentExceptions.StudentNotFoundException;
import com.SMS.schoolmanagementsystem.exception.SubjectExceptions.SubjectNotFoundException;
import com.SMS.schoolmanagementsystem.mapper.AttendanceMapper;
import com.SMS.schoolmanagementsystem.repository.AttendanceRepository;
import com.SMS.schoolmanagementsystem.repository.EnrollmentRepository;
import com.SMS.schoolmanagementsystem.repository.StudentRepository;
import com.SMS.schoolmanagementsystem.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepo;
    private final EnrollmentRepository enrollmentRepo;
    private final StudentRepository studentRepo;
    private final SubjectRepository subjectRepo;
    private final AttendanceMapper attendanceMapper;


     //without pagination
//    public List<AttendanceResponseDto> findAllAttendance() {
//        List<Attendance> attendances = attendanceRepo.findAll();
//        List<AttendanceResponseDto> responses = new ArrayList<>();
//
//        for(Attendance attendance : attendances) {
//            AttendanceResponseDto attendanceResponse = mapToResponse(attendance);
//            responses.add(attendanceResponse);
//        }
//        return responses;
//    }

    public Page<AttendanceResponseDto> getAll(Pageable pageable){
        Page<Attendance> attendancePage = attendanceRepo.findAll(pageable);
        return attendancePage.map(attendanceMapper::toResponse);
    }

    public AttendanceResponseDto findById(Long id) {
        Attendance attendance = attendanceRepo.findById(id)
                .orElseThrow(() -> new AttendanceNotFound(id));
        return attendanceMapper.toResponse(attendance);
    }

    public Page<AttendanceResponseDto> findByStudentId(Long id, Pageable pageable) {

        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        List<Enrollment> enrollment = enrollmentRepo.findByStudent(student);
        Page<Attendance> attendances = attendanceRepo.findAttendanceByEnrollmentIn(enrollment, pageable);

        return attendances.map(attendanceMapper::toResponse);

//        List<AttendanceResponseDto> responses = new ArrayList<>();
//
//       for(Attendance attendance : attendances) {
//           AttendanceResponseDto attendanceResponse = mapToResponse(attendance);
//           responses.add(attendanceResponse);
//       }
//       return responses;
    }

    public PercentageResponseDto getPercentage(Long id) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        List<Enrollment> enrollment = enrollmentRepo.findByStudent(student);

        long totalClasses = attendanceRepo.countTotalClasses(enrollment);
        long presentClasses = attendanceRepo.countPresentClasses(enrollment, AttendanceEnum.PRESENT);

        double percentage = 0.0;

        if(totalClasses > 0){
            percentage = (presentClasses * 100.0) / totalClasses;
            percentage = Math.round(percentage*100.0)/100.0;
        }

        PercentageResponseDto response = new PercentageResponseDto();
        response.setStudentId(student.getId());
        response.setStudentName(student.getFirstName() + " " + student.getLastName());
        response.setPercentage(percentage);
        response.setTotalClasses(totalClasses);
        response.setPresent(presentClasses);

        return response;

    }

    public Page<AttendanceResponseDto> findBySubjectId(Long id, Pageable pageable){

        Subject subject = subjectRepo.findById(id)
                .orElseThrow(() -> new SubjectNotFoundException(id));

        List<Enrollment> enrollment = enrollmentRepo.findBySubject(subject);
        Page<Attendance> attendances = attendanceRepo.findAttendanceByEnrollmentIn(enrollment, pageable);

        return attendances.map(attendanceMapper::toResponse);
//        List<AttendanceResponseDto> responses = new ArrayList<>();
//
//        for(Attendance attendance : attendances){
//            responses.add(mapToResponse(attendance));
//        }
//        return responses;
    }

    public Page<AttendanceResponseDto> findByDate(LocalDate date, Pageable pageable) {
        Page<Attendance> attendance = attendanceRepo.findAllByAttendanceDate(date, pageable);

        return attendance.map(this::mapToResponse);
//        List<AttendanceResponseDto> responses = new ArrayList<>();
//
//        for(Attendance attendances : attendance){
//
//            AttendanceResponseDto attendanceResponse = mapToResponse(attendances);
//            responses.add(attendanceResponse);
//
////            if(!attendanceResponse.equals(" ") && attendanceResponse != null) {
////            }
////            throw new EmptyAttendanceException(date);
//
//        }
//        return responses;
    }



    public AttendanceResponseDto create(AttendanceRequestDto dto) {

        Attendance attendance = attendanceMapper.toEntity(dto);
        Attendance saved = attendanceRepo.save(attendance);
        return attendanceMapper.toResponse(saved);

//        Attendance attendance = new Attendance();
//
//        Enrollment enrollment = enrollmentRepo.findById(request.getEnrollmentId())
//                .orElseThrow(() -> new EnrollmentNotFoundException(request.getEnrollmentId()));
//
//
//        if(enrollment.getStatus() == EnrollmentStatusEnum.COMPLETED){
//            throw new CompletedException(enrollment.getStatus());
//        }
//
//        if(enrollment.getStatus() == EnrollmentStatusEnum.DROPPED) {
//            throw new DroppedAttendanceException(enrollment.getStatus());
//        }
//
//        if(attendanceRepo.existsByAttendanceDateAndEnrollment(LocalDate.now(), enrollment)) {
//            throw new DuplicateDateException(request.getEnrollmentId(), LocalDate.now());
//        }

//        attendance.setEnrollment(enrollment);
//        attendance.setAttendanceDate(LocalDate.now());
//        attendance.setMarkedAt(LocalTime.now());
//        attendance.setStatus(request.getStatus());
//        Attendance saved = attendanceRepo.save(attendance);
//
//        return mapToResponse(saved);
    }

    public void deleteById(Long id){
        attendanceRepo.deleteById(id);
    }

    public void deleteAll(){
        attendanceRepo.deleteAll();
    }


    private AttendanceResponseDto mapToResponse(Attendance attendance) {

        AttendanceResponseDto response = new AttendanceResponseDto();

        response.setAttendanceId(attendance.getId());
        response.setEnrollmentId(attendance.getEnrollment().getEnrollmentId());
        response.setStudentId(attendance.getEnrollment().getStudent().getId());
        response.setStudentName(attendance.getEnrollment().getStudent().getFirstName()
                + " " + attendance.getEnrollment().getStudent().getLastName() );
        response.setSubjectName(attendance.getEnrollment().getSubject().getName());
        response.setSubjectId(attendance.getEnrollment().getSubject().getId());
        response.setStatus(attendance.getStatus());
        response.setDate(attendance.getAttendanceDate());
        response.setMarkedAt(attendance.getMarkedAt());

        return response;
    }
}
