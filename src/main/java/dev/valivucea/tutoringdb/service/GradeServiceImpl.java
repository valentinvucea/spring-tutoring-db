package dev.valivucea.tutoringdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.valivucea.tutoringdb.model.Grade;
import dev.valivucea.tutoringdb.repository.GradeRepository;

@Service
public class GradeServiceImpl implements GradeService {

    private GradeRepository gradeRepository;

    @Autowired
    public GradeServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }
    
    @Override
    public Grade createGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    @Override
    public List<Grade> getGradeList() {
        return (List<Grade>) gradeRepository.findAll();
    }

    @Override
    public Grade updateGrade(Grade grade, Long id) {
        Grade oldGrade = gradeRepository.findById(id).get();

        return gradeRepository.save(oldGrade);
    }

    @Override
    public void deleteGrade(Long id) {
        gradeRepository.deleteById(id);
    }
}
