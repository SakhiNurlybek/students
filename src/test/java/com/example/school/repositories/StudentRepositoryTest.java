package com.example.school.repositories;

import com.example.school.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:test.properties")
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testCreateNewStudent(){

        Student savedStudent = studentRepository.save(new Student(null,"sakhi.nurlybek@gmail.com","asdasd",
                "Nurlybek","Sakhi",null));

        assertNotNull(savedStudent);
        assertTrue(savedStudent.getId()>0);

    }

}