package dev.valivucea.tutoringdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dev.valivucea.tutoringdb.model.Course;
import dev.valivucea.tutoringdb.model.Grade;
import dev.valivucea.tutoringdb.model.Subject;
import dev.valivucea.tutoringdb.service.CourseService;
import dev.valivucea.tutoringdb.service.GradeService;
import dev.valivucea.tutoringdb.service.SubjectService;

@Controller
public class CoursesController {

    @Autowired
    private CourseService courseService;
    
    @Autowired
    private GradeService gradeService;
    
    @Autowired
    private SubjectService subjectService;         
    
    @GetMapping({"/courses", "courses/", "courses/index"})
    public String showListPage(Model model) {
        List<Course> courses = courseService.getCourseList();
        model.addAttribute("courses", courses);
        
        return "/courses/index";
    }

    @GetMapping({"courses/add"})
    public String showAddCourseForm(Model model) {
        List<Grade> grades     = gradeService.getGradeList();
        List<Subject> subjects = subjectService.getSubjectList();

        model.addAttribute("grades", grades);
        model.addAttribute("subjects", subjects);        
        model.addAttribute("course", new Course());
        
        return "/courses/add";
    }
    
    @PostMapping({"courses/add"})
    public String createNewGrade(@ModelAttribute Course course, Model model) {
        courseService.createCourse(course);
        
        return "redirect:/courses/index";
    }

    @GetMapping({"courses/edit/{id}"})
    public String showEditCourseForm(@PathVariable("id") long id, Model model) {
        Course course = courseService.getCourseById(id);
        List<Grade> grades     = gradeService.getGradeList();
        List<Subject> subjects = subjectService.getSubjectList();        

        model.addAttribute("grades", grades);
        model.addAttribute("subjects", subjects);    
        model.addAttribute("course", course);

        return "/courses/edit";
    }
    
    @PostMapping("courses/edit/{id}")
    public String updateCourse(@PathVariable("id") long id, @ModelAttribute Course course, Model model) {
        courseService.updateCourse(course, id);

        return "redirect:/courses/index";
    }
    
    @GetMapping("courses/delete/{id}")
    public String deleteCourse(@PathVariable("id") long id, Model model) {
        courseService.deleteCourse(id);

        return "redirect:/courses/index";
    }     
}
