package dev.valivucea.tutoringdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import dev.valivucea.tutoringdb.model.Session;
import dev.valivucea.tutoringdb.repository.SessionRepository;

@Service
public class SessionServiceImpl implements SessionService {

    private SessionRepository sessionRepository;

    @Autowired
    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Session createSession(Session session) {
        return sessionRepository.save(session);
    }

    @Override
    public List<Session> getSessionList() {
        return (List<Session>) sessionRepository.findAll();
    }

    @Override
    public Session updateSession(Session session, Long id) {
        session.setId(id);
        return sessionRepository.save(session);
    }

    @Override
    public void deleteSession(Long id) {
        Session session = sessionRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Session not Found - Id: " + id));
        sessionRepository.delete(session);
    }

    @Override
    public Session getSessionById(Long id) {
        return sessionRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Session not Found - Id: " + id));
    }

    @Override
    public List<Session> getSessionListOrdered() {
        throw new UnsupportedOperationException("Unimplemented method 'getSessionListOrdered'");
    }

    @Override
    public Page<Session> getSessionListPage(int pageNumber, int pageSize) {
        throw new UnsupportedOperationException("Unimplemented method 'getSessionListPage'");
    }   
}
