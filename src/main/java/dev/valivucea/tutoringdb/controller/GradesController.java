package dev.valivucea.tutoringdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dev.valivucea.tutoringdb.model.Grade;
import dev.valivucea.tutoringdb.service.GradeService;

@Controller
public class GradesController {

    @Autowired
    private GradeService gradeService;

    @GetMapping({"/grades", "grades/", "grades/index"})
    public String showGradesList(Model model) {
        List<Grade> grades = gradeService.getGradeList();
        
        model.addAttribute("titleThis", "Spring Boot Tutorial | Grades List");
        model.addAttribute("grades", grades);

        return "/grades/index";
    }

    @GetMapping({"grades/add"})
    public String showAddGradeForm(Model model) {
        model.addAttribute("grade", new Grade());
        
        return "/grades/add";
    }
    
    @PostMapping({"grades/add"})
    public String createNewGrade(@ModelAttribute Grade grade, Model model) {
        gradeService.createGrade(grade);
        
        return "redirect:/grades/index";
    }
    
    @GetMapping({"grades/edit/{id}"})
    public String showEditGradeForm(@PathVariable("id") long id, Model model) {
        Grade grade = gradeService.getGradeById(id);   
        model.addAttribute("grade", grade);

        return "/grades/edit";
    }
    
    @PostMapping("grades/edit/{id}")
    public String updateGrade(@PathVariable("id") long id, @ModelAttribute Grade grade, Model model) {
        gradeService.updateGrade(grade, id);

        return "redirect:/grades/index";
    }

    @GetMapping("grades/delete/{id}")
    public String deleteGrade(@PathVariable("id") long id, Model model) {
        gradeService.deleteGrade(id);

        return "redirect:/grades/index";
    }    
}
