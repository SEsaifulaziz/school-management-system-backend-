package com.SMS.SchoolManagementSystem.repository;

import com.SMS.SchoolManagementSystem.entity.Attendance;
import com.SMS.SchoolManagementSystem.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Boolean existsByAttendanceDateAndEnrollment(LocalDate date, Enrollment enrollmentId);
    List<Attendance> findAllByAttendanceDate(LocalDate date);
    List<Attendance> findAttendanceByEnrollmentIn(List<Enrollment> enrollments);

}