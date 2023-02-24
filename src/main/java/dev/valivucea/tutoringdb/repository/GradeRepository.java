package dev.valivucea.tutoringdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.valivucea.tutoringdb.model.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    
    Grade findByShortName(String shortName);
}
