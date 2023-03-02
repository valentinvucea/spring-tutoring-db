package dev.valivucea.tutoringdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.valivucea.tutoringdb.model.Subject;
import dev.valivucea.tutoringdb.repository.SubjectRepository;

@Service
public class SubjectServiceImpl implements SubjectService {

    private SubjectRepository SubjectRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository SubjectRepository) {
        this.SubjectRepository = SubjectRepository;
    }
    
    @Override
    public Subject createSubject(Subject Subject) {
        return SubjectRepository.save(Subject);
    }

    @Override
    public List<Subject> getSubjectList() {
        return (List<Subject>) SubjectRepository.findAll();
    }

    @Override
    public Subject updateSubject(Subject Subject, Long id) {
        Subject.setId(id);
        return SubjectRepository.save(Subject);
    }

    @Override
    public void deleteSubject(Long id) {
        Subject Subject = SubjectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subject not found - Id: " + id));

        SubjectRepository.delete(Subject);
    }

    @Override
    public Subject getSubjectById(Long id) {
        return SubjectRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Subject not found - Id: " + id));
    }    
}
