package dev.valivucea.tutoringdb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("title", "Tutoring Page | Home Page");
        model.addAttribute("message", "Hello World!");

        return "/home/index";
    }
}
