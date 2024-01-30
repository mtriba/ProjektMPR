package com.example.demo;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class StudentRepositoryTests {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testSaveAndRetrieveStudent() {
        // Given
        Student student = new Student();
        student.setName("John");
        student.setLastName("Doe");
        student.setAge(25);

        // When
        Student savedStudent = studentRepository.save(student);
        Student retrievedStudent = studentRepository.findById(savedStudent.getId()).orElse(null);

        // Then
        assertTrue(retrievedStudent != null);
        assertEquals("John", retrievedStudent.getName());
        assertEquals("Doe", retrievedStudent.getLastName());
        assertEquals(25, retrievedStudent.getAge());
    }

}
