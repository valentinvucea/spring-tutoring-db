package dev.valivucea.tutoringdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.valivucea.tutoringdb.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
