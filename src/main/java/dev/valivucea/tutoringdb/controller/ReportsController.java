package dev.valivucea.tutoringdb.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dev.valivucea.tutoringdb.service.SessionService;
import dev.valivucea.tutoringdb.service.StudentSessionReport;

@Controller
public class ReportsController {
    
    @Autowired
    private SessionService sessionService;
    
    @GetMapping({"/reports", "/reports/", "/reports/{year}/{quarter}"})
    public String showIndexPage(
        Model model, 
        @PathVariable(required = false) Integer year,
        @PathVariable(required = false) Integer quarter
    ) {
        // Find the current year and quarter
        LocalDate today = LocalDate.now(); 
        if (year == null) {
            year = today.getYear();
        }
        if (quarter == null) {
            int month = today.getMonthValue();
            quarter = ((month - 1 ) / 3 ) + 1;
        }

        // Get the student data for the computed year and quarter
        HashMap<Long, StudentSessionReport> students = sessionService.getDurationByStudentAndQuarter(year, quarter);

        // Get the values for year dropdown (last 5 years)
        int[] years = IntStream.rangeClosed(year - 5, year).toArray();

        // Get the values for quarter dropdown (1 to 4)
        int[] quarters = IntStream.rangeClosed(1, 4).toArray();;

        // Send the values to view
        model.addAttribute("students", students);
        model.addAttribute("year", year);
        model.addAttribute("years", years);
        model.addAttribute("quarter", quarter);
        model.addAttribute("quarters", quarters);

        // Return the view template path
        return "reports/index";
    }


}
