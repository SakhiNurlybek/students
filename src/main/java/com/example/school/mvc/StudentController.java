package com.example.school.mvc;

import com.example.school.entity.Student;
import com.example.school.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping(value = "/")
    public String getAllStudents(){

        return "index";
    }

    private Student getStudentData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            User secUser = (User) authentication.getPrincipal();
            Student myStudent = studentService.getStudentByEmail(secUser.getUsername());
            return myStudent;
        }
        return null;
    }
}
