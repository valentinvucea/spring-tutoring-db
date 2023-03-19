package dev.valivucea.tutoringdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.valivucea.tutoringdb.model.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
}
