package com.SMS.SchoolManagementSystem.repository;

import com.SMS.SchoolManagementSystem.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Boolean existsByAttendanceDate(LocalDate date);
}