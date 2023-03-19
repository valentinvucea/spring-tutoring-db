package dev.valivucea.tutoringdb.service;

import java.util.List;
import org.springframework.data.domain.Page;
import dev.valivucea.tutoringdb.model.Session;

public interface SessionService {
    // Create a new session
    Session createSession(Session session);
    
    // Read all sessions
    List<Session> getSessionList();
    
    // Update an existing session
    Session updateSession(Session session, Long id);

    // Delete session
    void deleteSession(Long id);

    // Retrieve an existing session
    Session getSessionById(Long id);

    // Get list of sessions ordered
    List<Session> getSessionListOrdered();

    // Get list of sessions paginated
    Page<Session> getSessionListPage(int pageNumber, int pageSize);
}
