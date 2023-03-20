package dev.valivucea.tutoringdb.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import dev.valivucea.tutoringdb.model.Session;
import dev.valivucea.tutoringdb.model.Student;
import dev.valivucea.tutoringdb.model.Subject;
import dev.valivucea.tutoringdb.service.SessionService;
import dev.valivucea.tutoringdb.service.StudentService;
import dev.valivucea.tutoringdb.service.SubjectService;

@Controller
public class SessionsController {
    
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SessionService sessionService;

    @GetMapping({"/sessions", "sessions/"})
    public String showIndexPage(Model model) {
        List<Student> students = studentService.getStudentList();
        List<Subject> subjects = subjectService.getSubjectList();
        List<Session> sessions = sessionService.getSessionList();

        model.addAttribute("pageTitle", "Sessions List");
        model.addAttribute("sessions", sessions);
        model.addAttribute("subjects", subjects);
        model.addAttribute("students", students);

        return "sessions/index";
    }

    @GetMapping({"sessions/add"})
    public String showAddSessionForm(Model model) {
        List<Subject> subjects = subjectService.getSubjectList();
        List<Student> students = studentService.getStudentList();
        Session session = new Session();
        session.setSessionDate(new Date());

        model.addAttribute("subjects", subjects);
        model.addAttribute("students", students);
        model.addAttribute("entry", session);
        
        return "sessions/add";
    }
    
    @PostMapping({"sessions/add"})
    public String createNewGrade(@ModelAttribute Session entry, Model model) {
        sessionService.createSession(entry);
        
        return "redirect:/sessions/";
    }    
    
}
