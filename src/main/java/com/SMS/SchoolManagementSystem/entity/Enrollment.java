package com.SMS.SchoolManagementSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@RequiredArgsConstructor
@Table(
        name = "enrollments",
        uniqueConstraints = {

                @UniqueConstraint(columnNames = {"student_id", "subject_id"})
        }
)
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrollmentId;

    // ---------------- Relationships ----------------

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name =  "student_id")
    private Student student;


    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    // ---------------- Lifecycle ----------------

    @Column(nullable = false)
    private LocalDate enrollmentDate;

    // ---------------- Relationships ----------------

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnrollmentStatusEnum status = EnrollmentStatusEnum.ACTIVE;

    // ---------------- Outcome ----------------

    private String finalGrade;

}
