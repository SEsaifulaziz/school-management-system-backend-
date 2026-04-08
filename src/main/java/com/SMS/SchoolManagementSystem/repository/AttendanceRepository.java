package com.SMS.schoolmanagementsystem.repository;

import com.SMS.schoolmanagementsystem.entity.Attendance;
import com.SMS.schoolmanagementsystem.entity.Enrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Boolean existsByAttendanceDateAndEnrollment(LocalDate date, Enrollment enrollmentId);

    Page<Attendance> findAllByAttendanceDate(LocalDate date, Pageable pageable);

    Page<Attendance> findAttendanceByEnrollmentIn(List<Enrollment> enrollments, Pageable pageable);

    @Query("SELECT COUNT(a) FROM Attendance a WHERE a.enrollment IN :enrollmets")
    long countTotalClasses(List<Enrollment> enrollments);

    @Query("SELECT COUNT(a) FROM Attendance a WHERE a.enrollment IN :enrollments AND a.status = 'PRESENT'")
    long countPresentClasses(List<Enrollment> enrollments);

    Page<Attendance> findAll(Pageable pageable);

}