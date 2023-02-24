package dev.valivucea.tutoringdb.service;

import java.util.List;

import dev.valivucea.tutoringdb.model.Grade;

public interface GradeService {
    // Create a new grade
    Grade createGrade(Grade grade);
    
    // Read all grades
    List<Grade> getGradeList();
    
    // Update an existing grade
    Grade updateGrade(Grade grade, Long id);

    // Delete operation
    void deleteGrade(Long id);
}
