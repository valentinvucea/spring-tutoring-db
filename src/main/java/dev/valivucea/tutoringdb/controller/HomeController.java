package dev.valivucea.tutoringdb.controller;

import java.time.LocalDate;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dev.valivucea.tutoringdb.service.SessionService;
import dev.valivucea.tutoringdb.service.StudentSessionReport;

@Controller
public class HomeController {
    
    @Autowired
    private SessionService sessionService;
    
    @GetMapping("/")
    public String showHomePage(Model model) {
        // Find the current year and quarter
        LocalDate today = LocalDate.now(); 
        int year = today.getYear();
        int month = today.getMonthValue();
        int quarter = ((month - 1 ) / 3 ) + 1;

        // Get the student data for the computed year and quarter
        HashMap<Long, StudentSessionReport> students = sessionService.getDurationByStudentAndQuarter(year, quarter);

        // Send the date to view
        model.addAttribute("students", students);

        // Return the view template path
        return "home/index";
    }


}
