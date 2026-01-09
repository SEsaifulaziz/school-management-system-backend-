School Management System Backend
A backend system built with Spring Boot and MySQL that models real academic
workflows such as student management, subject management, enrollment lifecycle,
attendance tracking, and grading using clean architecture and state-driven business rules.

This project focuses on learning real backend engineering concepts beyond basic CRUD
operations.
________________________________________

## Features
# Core Academic Domain

# Student Management
  •	Create, update, delete, and fetch students
  •	Email uniqueness validation
  •	Field validation using Bean Validation
  •	Global exception handling for invalid operations

# Subject Management
  •	Create, update, delete, and fetch subjects
  •	Unique subject codes
  •	Grade-level based subjects
  •	Validation and duplicate prevention

# Enrollment System
  •	Enroll students into subjects
  • Prevent duplicate enrollments
  •	Lifecycle states:
      •	ACTIVE
      •	COMPLETED
      •	DROPPED
  •	State-driven rules:
  •	Cannot modify COMPLETED or DROPPED enrollments
  •	Status transitions are validated
  •	Grade assignment with lifecycle validation

# Attendance Management
  •	Mark attendance for enrolled students
  •	Fetch attendance by ID and by date
  •	Fetch attendance history
  •	Prevent duplicate attendance for the same enrollment and date
  •	Enforce enrollment state rules:
      •	Attendance cannot be marked for COMPLETED enrollments
      •	Attendance cannot be marked for DROPPED enrollments
________________________________________

# Architecture & Design
  •	Layered architecture (Controller → Service → Repository)
  •	DTO-based request and response models
  •	State-driven business rules
  • Global exception handling
  •	Validation using Bean Validation
  •	Clean RESTful API design
  •	Relational data modeling with JPA
________________________________________

# Tech Stack
  •	Java
  •	Spring Boot
  •	Spring Data JPA
  •	Hibernate
  •	MySQL
  •	Lombok
  •	Maven
________________________________________

# Project Structure
controller    → REST endpoints
service       → Business logic & rules
repository    → Database access layer
entity        → JPA entities
dtos          → Request & response models
exception     → Custom exceptions & global handler
________________________________________

# Domain Model
Student → Enrollment → Attendance
Subject → Enrollment → Attendance
  •	Students enroll in subjects through Enrollments
  •	Attendance is tracked per enrollment per date
  •	Grades are assigned per enrollment
  •	Business rules are enforced at the service layer
________________________________________

# Business Rules
  •	Student email must be unique
  •	Subject codes must be unique
  •	A student can only enroll once per subject
  •	Enrollment must be ACTIVE to allow attendance
  •	Attendance can only be marked once per enrollment per date
  •	Grades can only be assigned to COMPLETED enrollments
  •	Enrollment lifecycle transitions are validated
________________________________________

# API Capabilities
Students
  •	Create student
  •	Update student
  •	Get all students
  •	Get student by ID
  •	Delete student
  •	Delete all students

# Subjects
  •	Create subject
  •	Update subject
  •	Get all subjects
  •	Get subject by ID
  •	Delete subject
  •	Delete all subjects

# Enrollments 
  •	Enroll student into subject
  •	Update enrollment status
  •	Assign final grade
  •	Get all enrollments
  •	Get enrollment by ID
  •	Delete enrollment
  •	Enforce lifecycle rules
 
# Attendance
  •	Mark attendance
  •	Get attendance by ID
  • Get attendance by date
  •	Get attendance history
  •	Delete attendance
  •	Prevent duplicates
________________________________________

# Learning Goals of This Project
This project is built to practice:
  •	Backend architecture design
  •	State-based business logic
  •	DTO-driven APIs
  •	Exception-driven validation
  •	Relational data modeling
  •	Real-world API workflows
  •	Database integrity protection
________________________________________

# Future Enhancements
  •	Pagination and sorting
  •	Teacher management
  •	Authentication and role-based access
  •	Attendance reports
  •	Performance optimization
  •	Caching
  •	API documentation (Swagger)
________________________________________

## Author
Saiful Aziz
Backend Developer (Java & Spring Boot)
