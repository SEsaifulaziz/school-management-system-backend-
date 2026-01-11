package com.SMS.SchoolManagementSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Enrollment enrollment;

    @NotNull
//    @Column(unique = true)
    LocalDate attendanceDate;

    @NotNull
    private LocalTime markedAt;

    @NotNull
    private AttendenceEnum status;

}
