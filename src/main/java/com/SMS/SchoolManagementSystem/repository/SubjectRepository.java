package com.SMS.SchoolManagementSystem.repository;

import com.SMS.SchoolManagementSystem.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


@Component
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Boolean existsByCode(String code);

}
