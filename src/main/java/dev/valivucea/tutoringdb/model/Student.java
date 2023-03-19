package dev.valivucea.tutoringdb.model;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", length = 80, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 120, nullable = false)
    private String lastName;

    @Column(length = 80, nullable = false)
    private String email;

    @Column(length = 14, nullable = false)
    private String phone;

    @ManyToOne(targetEntity = Grade.class)
    @JoinColumn(name = "grade_id")
    private Grade grade;    

    @Column(name = "parent_name")
    private String parentName;

    @Column(name = "parent_phone", length = 14)
    private String parentPhone;

    @Column(name = "parent_email", length = 80)
    private String parentEmail;

    @Column(columnDefinition="TEXT", length = 4000)
    private String comments;
    
    @Column(nullable = false)
    @ColumnDefault(value = "0")
    private int active;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getParentName() {
        return parentName;
    }
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
    public String getParentPhone() {
        return parentPhone;
    }
    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }
    public String getParentEmail() {
        return parentEmail;
    }
    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public int getActive() {
        return active;
    }
    public void setActive(int active) {
        this.active = active;
    }
    public Grade getGrade() {
        return grade;
    }
    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }    
}
