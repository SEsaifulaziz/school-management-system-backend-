package com.SMS.SchoolManagementSystem.repository;

import com.SMS.SchoolManagementSystem.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

}