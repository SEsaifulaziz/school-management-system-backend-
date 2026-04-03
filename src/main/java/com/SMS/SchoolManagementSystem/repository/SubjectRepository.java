package com.SMS.schoolmanagementsystem.repository;

import com.SMS.schoolmanagementsystem.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;


@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Boolean existsByCode(String code);
    Page<Subject> findAll(Pageable pageable);

}
