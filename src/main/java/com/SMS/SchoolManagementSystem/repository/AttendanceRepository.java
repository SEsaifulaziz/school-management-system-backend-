package com.SMS.SchoolManagementSystem.repository;

import com.SMS.SchoolManagementSystem.entity.Attendance;
import com.SMS.SchoolManagementSystem.entity.Enrollment;
import com.SMS.SchoolManagementSystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Boolean existsByAttendanceDate(LocalDate date);
    List<Attendance> findAllByAttendanceDate(LocalDate date);
//    List<Attendance> findByStudent(Enrollment student);
}