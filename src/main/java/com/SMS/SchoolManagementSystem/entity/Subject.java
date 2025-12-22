package com.SMS.SchoolManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (
        name = "Subjects",
        uniqueConstraints = @UniqueConstraint(columnNames = "code")
)
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private String gradeLevel;

    @Column(nullable = false)
    private int creditHours;

    @Column(nullable = false)
    private Boolean active = true;
}
