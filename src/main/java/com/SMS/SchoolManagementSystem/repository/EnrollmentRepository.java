package com.SMS.SchoolManagementSystem.repository;

import com.SMS.SchoolManagementSystem.entity.Enrollment;
import com.SMS.SchoolManagementSystem.entity.EnrollmentStatusEnum;
import com.SMS.SchoolManagementSystem.entity.Student;
import com.SMS.SchoolManagementSystem.entity.Subject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.w3c.dom.ls.LSInput;

import java.util.List;

@Component
public interface  EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findBySubject(Subject subjectId);
    List<Enrollment> findByStudent(Student studentId);
    List<Enrollment> findByStudentAndStatus(Student studentId, EnrollmentStatusEnum statusEnum);
//    List<Enrollment> findByStudentAndCompletedStatus(Student studentId, EnrollmentStatusEnum statusEnum);
//    List<Enrollment> findByStudentAndStatusIn(Student studentId, List<EnrollmentStatusEnum> statuses);
    boolean existsByStudentAndSubject(Student studentId, Subject subject);
//    Enrollment findByStudentOrderByEnrollmentDataDesc(Long id, Enrollment enrollment);

    }
