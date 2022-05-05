package com.example.school.rest;

import com.example.school.entity.Student;
import com.example.school.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class StudentRestController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students/list")
    public List<Student> listAll(){
        return this.studentRepository.findAll();
    }

    @PostMapping("/students/add")
    public Student addStudent(
            @RequestBody Student student){
        return this.studentRepository.save(student);
    }

}
