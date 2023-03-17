package dev.valivucea.tutoringdb.service;

import java.util.List;

import org.springframework.data.domain.Page;

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

    // Get list of students paginated
    Page<Student> getStudentListPage(int pageNumber, int pageSize);
}
