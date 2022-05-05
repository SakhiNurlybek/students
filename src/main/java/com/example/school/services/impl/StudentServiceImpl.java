package com.example.school.services.impl;

import com.example.school.entity.Roles;
import com.example.school.entity.Student;
import com.example.school.repositories.RoleRepository;
import com.example.school.repositories.StudentRepository;
import com.example.school.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Student student = studentRepository.findByEmail(username);

        if(student!=null){
            User myStudent = new User(student.getEmail(),student.getPassword(),student.getRoles());
            return myStudent;
        }
        throw new UsernameNotFoundException("User Not Found");
    }

    @Override
    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Override
    public Student createStudent(Student student) {
        Student checkStudent = studentRepository.findByEmail(student.getEmail());
        if(checkStudent == null){
            Roles role = roleRepository.findByRole("ROLE_USER");
            if(role!=null){

                ArrayList<Roles> roles = new ArrayList<>();
                roles.add(role);
                student.setRoles(roles);
                student.setPassword(passwordEncoder.encode(student.getPassword()));
                return  studentRepository.save(student);
            }
        }
        return null;
    }

    @Override
    public Student getUser(Long id) {
        return studentRepository.getById(id);
    }

    @Override
    public Student saveUser(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Roles> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Roles getRole(Long id) {
        return roleRepository.getById(id);
    }
}
