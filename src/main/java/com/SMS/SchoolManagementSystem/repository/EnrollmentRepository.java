package com.SMS.SchoolManagementSystem.repository;

import com.SMS.SchoolManagementSystem.entity.Enrollment;
import com.SMS.SchoolManagementSystem.entity.Student;
import com.SMS.SchoolManagementSystem.entity.Subject;
import io.swagger.v3.core.jackson.mixin.Schema31Mixin;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByStudentId(Long id);
    boolean existsByStudentAndSubject(Student studentId, Subject subject);

    boolean existsByEnrollmentId(Long id);

//    Enrollment findByStudentIdAndSubject(Long studentId, Long subjectId);

//    void deleteByStudentId(Long id);
}
