package com.SMS.schoolmanagementsystem.service;

import com.SMS.schoolmanagementsystem.dto.request.StudentRequestDto;
import com.SMS.schoolmanagementsystem.dto.response.StudentResponseDto;
import com.SMS.schoolmanagementsystem.dto.request.UpdateStudentRequestDto;
import com.SMS.schoolmanagementsystem.entity.Student;
import com.SMS.schoolmanagementsystem.exception.StudentExceptions.DuplicateEmailException;
import com.SMS.schoolmanagementsystem.exception.StudentExceptions.StudentNotFoundException;
import com.SMS.schoolmanagementsystem.mapper.StudentMapper;
import com.SMS.schoolmanagementsystem.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class StudentService {


    private final StudentRepository studentRepo;
    private final StudentMapper studentMapper;

    /********       without Pagination      *******/

//    public List<StudentResponseDto> getAll() {
//        List<Student> students = studentRepo.findAll();
//        List<StudentResponseDto> responses = new ArrayList<>();
//
//        // Simple forLoop
////        for(Student student: students){
////            responses.add(mapToResponse(student));
////        }
//
//        for (Student student : students) {
//            StudentResponseDto response = mapToResponse(student);
//            responses.add(response);
//        }
//        return responses;
//    }


    // with Pagination and map()
    public Page<StudentResponseDto> getAll(Pageable pageable) {
        Page<Student> studentPage = studentRepo.findAll(pageable);
        return studentPage.map(studentMapper::toResponse);
    }

    public StudentResponseDto findById(Long id) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return studentMapper.toResponse(student);
    }

    public StudentResponseDto createStudent(StudentRequestDto dto) {
        if (studentRepo.existsByEmail(dto.getEmail()))
            throw new DuplicateEmailException(dto.getEmail());

        Student student = studentMapper.toEntity(dto);
        Student saved = studentRepo.save(student);

        return studentMapper.toResponse(saved);
    }

    public Student deleteById(Long id) {
        studentRepo.deleteById(id);
        return null;
    }

    public void deleteAll() {
        studentRepo.deleteAll();
    }

    public StudentResponseDto updateStudent(Long id, UpdateStudentRequestDto updateRequest) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        // check email conflict ONLY if email is changed
        if (!student.getEmail().equals(updateRequest.getEmail())) {
            if (studentRepo.existsByEmail(updateRequest.getEmail())) {
                throw new DuplicateEmailException(updateRequest.getEmail());
            }
        }

        student.setFirstName(updateRequest.getFirstName());
        student.setLastName(updateRequest.getLastName());
        student.setEmail(updateRequest.getEmail());
        student.setEnrolledGrade(updateRequest.getEnrolledGrade());

        Student updatedStudent = studentRepo.save(student);

        return studentMapper.toResponse(updatedStudent);
    }

    //Manual Mapping
//    private StudentResponseDto mapToResponse(Student student) {
//        StudentResponseDto response = new StudentResponseDto();
//        response.setId(student.getId());
//        response.setFirstName(student.getFirstName());
//        response.setLastName(student.getLastName());
//        response.setEmail(student.getEmail());
//        response.setEnrolledGrade(student.getEnrolledGrade());
//        return response;
//    }
}
