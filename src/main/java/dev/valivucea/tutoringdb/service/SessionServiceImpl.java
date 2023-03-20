package dev.valivucea.tutoringdb.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.HashMap;
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

    public HashMap<Long, StudentSessionReport> getDurationByStudentAndQuarter(int year, int quarter) {
        // Intialize the collection that will be returned
        HashMap<Long, StudentSessionReport> results = new HashMap<Long, StudentSessionReport>();
        
        // Declare the variables used in the loop
        Date sessionDate;
        Long studentId;
        StudentSessionReport entry;

        // Compute the start and end date from year and quarter
        Date startDate = this.getFirstDayOfTheQuarter(year, quarter);
        Date endDate = this.getLastDayOfTheQuarter(year, quarter);

        // Get the list of sessions from the database
        List<Session> sessions = this.getSessionList();

        // Loop through all sessions
        for (Session session : sessions) {
            // Check the session date against the start and end of the quarter - we will add it only if is in between
            sessionDate = session.getSessionDate();

            if (startDate.compareTo(sessionDate) <= 0 && sessionDate.compareTo(endDate) <= 0) {
                // Set the unique key - the student ID from the session
                studentId = session.getStudent().getId();

                // If the key exists in results, we grab student data from it and update with the data from session 
                if (results.containsKey(studentId) == true) {
                    entry = results.get(studentId);
                    entry.updateHoursAndMinutes(session);

                    // Else, create a new student data report with the data from session
                } else {
                    entry = new StudentSessionReport(session);
                }
    
                // Finally, we add OR update the entry in the results (put will do both automatically)
                results.put(studentId, entry);
            }
        }

        return results;
    }

    protected Date getFirstDayOfTheQuarter(int year, int quarter) {
        int month = (quarter - 1) * 3 + 1;
        
        ZoneId z = ZoneId.of("America/New_York");
        LocalDate firstOfMonth = LocalDate.of(year, month, 1);
        
        ZonedDateTime zdt = firstOfMonth.atStartOfDay(z);
        Instant instant = zdt.toInstant();

        return java.util.Date.from(instant);
    }

    protected Date getLastDayOfTheQuarter(int year, int quarter) {
        int month = quarter * 3;
        
        ZoneId z = ZoneId.of("America/New_York");
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        LocalDate lastOfMonth = firstDayOfMonth.with(TemporalAdjusters.lastDayOfMonth());
                
        ZonedDateTime zdt = lastOfMonth.atStartOfDay(z).plusDays(1).minusSeconds(1);
        Instant instant = zdt.toInstant();

        return java.util.Date.from(instant);
    }    
}
