package com.example.demo;
import com.example.demo.controllers.StudentController;
import com.example.demo.exceptions.InvalidValue;
import com.example.demo.model.dto.StudentReadDTO;
import com.example.demo.model.dto.StudentWriteDTO;
import com.example.demo.services.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentControllerTests {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllStudents() {
        List<StudentReadDTO> mockStudents = Collections.singletonList(new StudentReadDTO());
        when(studentService.getAll()).thenReturn(mockStudents);

        ResponseEntity<List<StudentReadDTO>> response = studentController.getAllStudents();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockStudents, response.getBody());
        verify(studentService, times(1)).getAll();
    }

    @Test
    void testGetStudentById() {
        Long studentId = -1L;

        when(studentService.getById(studentId)).thenThrow(new InvalidValue("Nieprawidłowa wartość"));

        assertThrows(InvalidValue.class, () -> {
            ResponseEntity<StudentReadDTO> response = studentController.getStudentById(studentId);

            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            assertNull(response.getBody());
        });

        verify(studentService, times(1)).getById(studentId);
    }

    @Test
    void testDeleteStudentById() {
        Long studentId = 1L;

        ResponseEntity<Void> response = studentController.deleteStudentById(studentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(studentService, times(1)).deleteStudentById(studentId);
    }

    @Test
    void testSaveStudent() {
        StudentWriteDTO mockStudentWriteDTO = new StudentWriteDTO();

        ResponseEntity<Void> response = studentController.saveStudent(mockStudentWriteDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(studentService, times(1)).saveStudent(mockStudentWriteDTO);
    }

    @Test
    void testGetStudentsWithAgeGreaterThan() {
        Integer age = 20;
        List<StudentReadDTO> mockStudents = Collections.singletonList(new StudentReadDTO());
        when(studentService.getWithAgeGreatherThan(age)).thenReturn(mockStudents);

        ResponseEntity<List<StudentReadDTO>> response = studentController.getStudentsWithAgeGreaterThan(age);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockStudents, response.getBody());
        verify(studentService, times(1)).getWithAgeGreatherThan(age);
    }

    @Test
    void testGetStudentAge2timesById() {
        Long studentId = 1L;
        Integer mockAge2times = 42;
        when(studentService.getStudentAge2timesById(studentId)).thenReturn(mockAge2times);

        ResponseEntity<Integer> response = studentController.getStudentAge2timesById(studentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockAge2times, response.getBody());
        verify(studentService, times(1)).getStudentAge2timesById(studentId);
    }
}