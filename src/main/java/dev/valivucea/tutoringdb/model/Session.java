package dev.valivucea.tutoringdb.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date sessionDate;

    @JoinColumn(name = "student_id", nullable = false)
    @ManyToOne(targetEntity = Subject.class)
    private Student student;

    @JoinColumn(name = "grade_id", nullable = false)
    @ManyToOne(targetEntity = Grade.class)
    private Grade grade;    

    @Column(nullable = false)
    private int duration;

    @Column(columnDefinition="TEXT", length = 4000)
    private String lesson;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setSubject(Grade grade) {
        this.grade = grade;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

}
