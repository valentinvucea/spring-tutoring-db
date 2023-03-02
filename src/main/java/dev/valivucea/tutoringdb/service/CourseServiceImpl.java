package dev.valivucea.tutoringdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.valivucea.tutoringdb.model.Course;
import dev.valivucea.tutoringdb.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository CourseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository CourseRepository) {
        this.CourseRepository = CourseRepository;
    }
    
    @Override
    public Course createCourse(Course Course) {
        return CourseRepository.save(Course);
    }

    @Override
    public List<Course> getCourseList() {
        return (List<Course>) CourseRepository.findAll();
    }

    @Override
    public Course updateCourse(Course Course, Long id) {
        Course.setId(id);
        return CourseRepository.save(Course);
    }

    @Override
    public void deleteCourse(Long id) {
        Course Course = CourseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course not found - Id: " + id));

        CourseRepository.delete(Course);
    }

    @Override
    public Course getCourseById(Long id) {
        return CourseRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Course not found - Id: " + id));
    }    
}
