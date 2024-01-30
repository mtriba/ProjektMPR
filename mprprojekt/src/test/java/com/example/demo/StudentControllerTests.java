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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void testGetAllStudents() throws Exception {
        List<StudentReadDTO> mockStudents = Collections.singletonList(new StudentReadDTO());
        when(studentService.getAll()).thenReturn(mockStudents);

        mockMvc.perform(get("/students")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        verify(studentService, times(1)).getAll();
    }

    @Test
    void testGetStudentById() throws Exception {
        Long studentId = -1L;

        when(studentService.getById(studentId)).thenThrow(new InvalidValue("Nieprawidłowa wartość"));

        mockMvc.perform(get("/students/{id}", studentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()) // Oczekujemy statusu HTTP 404
                .andExpect(content().string("{\"message\":\"NieprawidÅowa wartoÅÄ\"}"));

        verify(studentService, times(1)).getById(studentId);
    }


    @Test
    void testDeleteStudentById() throws Exception {
        Long studentId = 1L;

        mockMvc.perform(delete("/students/{id}", studentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(studentService, times(1)).deleteStudentById(studentId);
    }

    @Test
    void testSaveStudent() throws Exception {
        StudentWriteDTO mockStudentWriteDTO = new StudentWriteDTO();
        mockStudentWriteDTO.setName("Mikolaj");
        mockStudentWriteDTO.setLastName("Kowalski");
        mockStudentWriteDTO.setAge(21);
        mockStudentWriteDTO.setStudentIndex("123241");

        mockMvc.perform(post("/students")
                        .content("{\"name\": \"Mikolaj\", \"lastName\": \"Kowalski\", \"age\": 21, \"studentIndex\": \"123241\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(studentService, times(1)).saveStudent(any(StudentWriteDTO.class));
    }


    @Test
    void testGetStudentsWithAgeGreaterThan() throws Exception {
        Integer age = 20;
        List<StudentReadDTO> mockStudents = Collections.singletonList(new StudentReadDTO());
        when(studentService.getWithAgeGreatherThan(age)).thenReturn(mockStudents);

        mockMvc.perform(get("/students/ageGreaterThan?age=" + age)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        verify(studentService, times(1)).getWithAgeGreatherThan(age);
    }


    @Test
    void testGetStudentAge2timesById() throws Exception {
        Long studentId = 1L;
        Integer mockAge2times = 42;
        when(studentService.getStudentAge2timesById(studentId)).thenReturn(mockAge2times);

        mockMvc.perform(get("/students/age2times/{id}", studentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("42"));

        verify(studentService, times(1)).getStudentAge2timesById(studentId);
    }
}