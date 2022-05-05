package com.example.school.services;

import com.example.school.entity.Roles;
import com.example.school.entity.Student;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService extends UserDetailsService {

    Student getStudentByEmail(String email);
    Student createStudent(Student student);
    Student getUser(Long id);
    Student saveUser(Student student);

    List<Student> getAllStudents();
    List<Roles> getAllRoles();
    Roles getRole(Long id);




}
