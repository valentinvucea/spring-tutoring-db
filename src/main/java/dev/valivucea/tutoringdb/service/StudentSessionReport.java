package dev.valivucea.tutoringdb.service;

import dev.valivucea.tutoringdb.model.Session;

public class StudentSessionReport {
    private String fullName;
    private int totalHours = 0;
    private int totalMinutes = 0;
    
    public StudentSessionReport(Session session) {
        this.fullName = session.getStudent().getFullName();
        this.totalHours = session.getDurationHours();
        this.totalMinutes = session.getDurationMinutes();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

    public int getTotalMinutes() {
        return totalMinutes;
    }

    public void setTotalMinutes(int totalMinutes) {
        this.totalMinutes = totalMinutes;
    }

    public void updateHoursAndMinutes(Session session) {
        int newMinutes = this.totalMinutes + session.getDurationMinutes();
        int newHoursFromMinutes = 0;
        
        if (newMinutes > 60) {           
            newHoursFromMinutes = newMinutes / 60;
            newMinutes = newMinutes - (60 * newHoursFromMinutes);
        }

        this.setTotalHours(this.totalHours + session.getDurationHours() + newHoursFromMinutes);
        this.setTotalMinutes(newMinutes);
    }

    public String getTotalDuration() {
        String duration = this.totalMinutes + " mins";
        if (this.totalHours > 0) {
            duration = this.totalHours + "h " + duration;
        }
        return duration;
    }
}
