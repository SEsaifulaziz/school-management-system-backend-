package com.SMS.schoolmanagementsystem.repository;

import com.SMS.schoolmanagementsystem.entity.Attendance;
import com.SMS.schoolmanagementsystem.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Boolean existsByAttendanceDateAndEnrollment(LocalDate date, Enrollment enrollmentId);
    List<Attendance> findAllByAttendanceDate(LocalDate date);
    List<Attendance> findAttendanceByEnrollmentIn(List<Enrollment> enrollments);

//    long coundByEnrollmentStudentId(Long studentId);
//    long countByEnrollmentStudentIdAndStatus(Long studentId, AttendenceEnum status);


}