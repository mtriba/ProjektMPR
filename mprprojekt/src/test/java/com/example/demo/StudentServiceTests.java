package com.example.demo;
import com.example.demo.exceptions.InvalidValue;
import com.example.demo.model.Student;
import com.example.demo.model.dto.StudentReadDTO;
import com.example.demo.model.dto.StudentWriteDTO;
import com.example.demo.repository.StudentRepository;
import com.example.demo.services.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StudentServiceTests {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;
    @BeforeEach
    public void clearedDataBase(){
        studentRepository.deleteAll();
    }

    @Test
    public void testGetAllStudents() {
        Student student1 = new Student();
        student1.setId(1L);

        Student student2 = new Student();
        student2.setId(2L);

        when(studentRepository.findAll()).thenReturn(Arrays.asList(student1, student2));

        List<StudentReadDTO> students = studentService.getAll();

        assertEquals(2, students.size());
    }

    @Test
    public void testGetStudentByIdShouldReturnStudentReadDTO() {
        Long validId = 1L;
        Student student = new Student();
        student.setId(validId);
        student.setName("John");

        when(studentRepository.findById(validId)).thenReturn(Optional.of(student));

        StudentReadDTO result = studentService.getById(validId);

        assertEquals("John", result.getName());
    }

    @Test
    public void testGetStudentByIdShouldThrowInvalidValueException() {
        Long invalidId = -1L;

        assertThrows(InvalidValue.class, () -> studentService.getById(invalidId));
    }

    @Test
    public void testDeleteStudentById() {
        Long validId = 1L;
        Student studentWithSubjects = new Student();
        studentWithSubjects.setId(validId);
        studentWithSubjects.setSubjects(new ArrayList<>()); // Assuming subjects is a list

        when(studentRepository.findById(validId)).thenReturn(Optional.of(studentWithSubjects));

        studentService.deleteStudentById(validId);

        verify(studentRepository, times(1)).deleteById(validId);
    }
    @Test
    public void testSaveStudent() {
        StudentWriteDTO studentWriteDTO = new StudentWriteDTO();

        studentService.saveStudent(studentWriteDTO);

        verify(studentRepository, times(1)).save(any());
    }

    @Test
    public void testGetWithAgeGreaterThan_ShouldReturnListOfStudentReadDTO() {
        Integer age = 20;
        List<Student> mockStudents = Arrays.asList(new Student(), new Student());

        when(studentRepository.findByAgeGreaterThan(age)).thenReturn(mockStudents);

        List<StudentReadDTO> result = studentService.getWithAgeGreatherThan(age);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetStudentAge2timesById_WithValidId_ShouldReturnTwiceTheAge() {
        Long validId = 1L;
        Student student = new Student();
        student.setId(validId);
        student.setAge(10);

        when(studentRepository.findById(validId)).thenReturn(Optional.of(student));

        Integer result = studentService.getStudentAge2timesById(validId);

        assertEquals(20, result);
    }

    @Test
    public void testDeleteStudentsWithAgeLessThan20_ShouldCallRepositoryMethod() {
        studentService.deleteStudenWithAgeLessThan20();

        verify(studentRepository, times(1)).deleteStudentsAgeLessThan20();
    }
}
