package com.SMS.schoolmanagementsystem.repository;

import com.SMS.schoolmanagementsystem.entity.Enrollment;
import com.SMS.schoolmanagementsystem.entity.EnrollmentStatusEnum;
import com.SMS.schoolmanagementsystem.entity.Student;
import com.SMS.schoolmanagementsystem.entity.Subject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findBySubject(Subject subjectId);

    List<Enrollment> findByStudent(Student studentId);

    List<Enrollment> findByStudentAndStatusIn(Student studentId, List<EnrollmentStatusEnum> statuses);

    Page<Enrollment> findAll(Pageable pageable);

    boolean existsByStudentAndSubject(Student studentId, Subject subject);

    //    Enrollment findByStudentOrderByEnrollmentDataDesc(Long id, Enrollment enrollment);

    }
