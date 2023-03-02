package dev.valivucea.tutoringdb.service;

import java.util.List;

import dev.valivucea.tutoringdb.model.Course;

public interface CourseService {
    // Create a new Course
    Course createCourse(Course course);
    
    // Read all Courses
    List<Course> getCourseList();
    
    // Update an existing Course
    Course updateCourse(Course Course, Long id);

    // Delete operation
    void deleteCourse(Long id);

    // Retrieve an existing Course
    Course getCourseById(Long id);
}
