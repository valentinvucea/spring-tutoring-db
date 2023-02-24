package dev.valivucea.tutoringdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dev.valivucea.tutoringdb.model.Grade;
import dev.valivucea.tutoringdb.service.GradeService;

@Controller
public class GradesController {

    @Autowired
    private GradeService gradeService;

    @GetMapping({"/grades", "grades/", "grades/index"})
    public String showGradesList(Model model) {
        List<Grade> grades = gradeService.getGradeList();
        
        model.addAttribute("title", "Spring Boot Tutorial | Grades List");
        model.addAttribute("grades", grades);

        return "/grades/index";
    }
}
