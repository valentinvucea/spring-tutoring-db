package dev.valivucea.tutoringdb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dev.valivucea.tutoringdb.model.Student;
import dev.valivucea.tutoringdb.model.Grade;
import dev.valivucea.tutoringdb.service.StudentService;
import dev.valivucea.tutoringdb.service.GradeService;

@Controller
public class StudentsController {

    @Autowired
    private StudentService studentService;
    
    @Autowired
    private GradeService gradeService;
       
    @GetMapping({"/students", "/students/", "/students/index", "/students/{page}", "students/{page}"})
    public String showListPage(Model model, @PathVariable(required = false) Integer page) {
        // List<Student> students = studentService.getStudentListOrdered();
        int currentPage = 1;
        if (page != null) {
            currentPage = page;
        }
    
        Page<Student> pageData = studentService.getStudentListPage(currentPage, 10);    
        model.addAttribute("students", pageData.getContent());
        model.addAttribute("current", currentPage);
        model.addAttribute("total", pageData.getTotalPages());
        
        return "students/index";
    }

    @GetMapping({"students/add"})
    public String showAddStudentForm(Model model) {
        List<Grade> grades = gradeService.getGradeList();

        model.addAttribute("grades", grades);
        model.addAttribute("student", new Student());
        
        return "students/add";
    }
    
    @PostMapping({"students/add"})
    public String createNewGrade(@ModelAttribute Student student, Model model) {
        studentService.createStudent(student);
        
        return "redirect:/students/index";
    }

    @GetMapping({"students/edit/{id}"})
    public String showEditStudentForm(@PathVariable("id") long id, Model model) {
        Student student = studentService.getStudentById(id);
        List<Grade> grades = gradeService.getGradeList();

        model.addAttribute("grades", grades);
        model.addAttribute("student", student);

        return "students/edit";
    }
    
    @PostMapping("students/edit/{id}")
    public String updateStudent(@PathVariable("id") long id, @ModelAttribute Student student, Model model) {
        studentService.updateStudent(student, id);

        return "redirect:/students/index";
    }
    
    @GetMapping("students/delete/{id}")
    public String deleteStudent(@PathVariable("id") long id, Model model) {
        studentService.deleteStudent(id);

        return "redirect:/students/index";
    }
    
    @GetMapping({"students/view/{id}"})
    public String showViewStudentForm(@PathVariable("id") long id, Model model) {
        Student student = studentService.getStudentById(id);
        List<Grade> grades = gradeService.getGradeList();

        model.addAttribute("grades", grades);
        model.addAttribute("student", student);

        return "students/view";
    }    
}
