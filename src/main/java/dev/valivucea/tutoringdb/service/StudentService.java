package dev.valivucea.tutoringdb.service;

import java.util.List;

import dev.valivucea.tutoringdb.model.Student;

public interface StudentService {
    // Create a new Course
    Student createStudent(Student student);
    
    // Read all Courses
    List<Student> getStudentList();
    
    // Update an existing Course
    Student updateStudent(Student Course, Long id);

    // Delete operation
    void deleteStudent(Long id);

    // Retrieve an existing Course
    Student getStudentById(Long id);

    // Get list of students ordered
    List<Student> getStudentListOrdered();
}
