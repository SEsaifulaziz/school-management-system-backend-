package com.SMS.SchoolManagementSystem.repository;

import com.SMS.SchoolManagementSystem.entity.Enrollment;
import com.SMS.SchoolManagementSystem.entity.EnrollmentStatusEnum;
import com.SMS.SchoolManagementSystem.entity.Student;
import com.SMS.SchoolManagementSystem.entity.Subject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.w3c.dom.ls.LSInput;

import java.util.List;

@Repository
public interface  EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findBySubject(Subject subjectId);

    List<Enrollment> findByStudent(Student studentId);

    List<Enrollment> findByStudentAndStatusIn(Student studentId, List<EnrollmentStatusEnum> statuses);

    boolean existsByStudentAndSubject(Student studentId, Subject subject);

    //    Enrollment findByStudentOrderByEnrollmentDataDesc(Long id, Enrollment enrollment);

    }
