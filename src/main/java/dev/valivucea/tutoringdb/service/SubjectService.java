package dev.valivucea.tutoringdb.service;

import java.util.List;

import dev.valivucea.tutoringdb.model.Subject;

public interface SubjectService {
    // Create a new Subject
    Subject createSubject(Subject Subject);
    
    // Read all Subjects
    List<Subject> getSubjectList();
    
    // Update an existing Subject
    Subject updateSubject(Subject Subject, Long id);

    // Delete operation
    void deleteSubject(Long id);

    // Retrieve an existing Subject
    Subject getSubjectById(Long id);
}
