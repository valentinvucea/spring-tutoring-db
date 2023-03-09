package dev.valivucea.tutoringdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dev.valivucea.tutoringdb.model.Subject;
import dev.valivucea.tutoringdb.service.SubjectService;

@Controller
public class SubjectsController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping({"/subjects", "subjects/", "subjects/index"})
    public String showsubjectsList(Model model) {
        List<Subject> subjects = subjectService.getSubjectList();
        model.addAttribute("subjects", subjects);

        return "subjects/index";
    }

    @GetMapping({"subjects/add"})
    public String showAddSubjectForm(Model model) {
        model.addAttribute("subject", new Subject());
        
        return "/subjects/add";
    }
    
    @PostMapping({"subjects/add"})
    public String createNewSubject(@ModelAttribute Subject Subject, Model model) {
        subjectService.createSubject(Subject);
        
        return "redirect:/subjects/index";
    }
    
    @GetMapping({"subjects/edit/{id}"})
    public String showEditSubjectForm(@PathVariable("id") long id, Model model) {
        Subject Subject = subjectService.getSubjectById(id);   
        model.addAttribute("subject", Subject);

        return "subjects/edit";
    }
    
    @PostMapping("subjects/edit/{id}")
    public String updateSubject(@PathVariable("id") long id, @ModelAttribute Subject Subject, Model model) {
        subjectService.updateSubject(Subject, id);

        return "redirect:/subjects/index";
    }

    @GetMapping("subjects/delete/{id}")
    public String deleteSubject(@PathVariable("id") long id, Model model) {
        subjectService.deleteSubject(id);

        return "redirect:/subjects/index";
    }    
}
