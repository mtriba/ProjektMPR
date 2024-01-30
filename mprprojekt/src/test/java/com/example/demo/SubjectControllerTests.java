package com.example.demo;

import com.example.demo.model.dto.SubjectReadDTO;
import com.example.demo.model.dto.SubjectWriteDTO;
import com.example.demo.services.SubjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Collections;
import java.util.List;
import org.mockito.ArgumentCaptor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SubjectControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubjectService subjectService;

    @BeforeEach
    void setUp() {
        List<SubjectReadDTO> mockSubjects = Collections.singletonList(new SubjectReadDTO());
        when(subjectService.getAll()).thenReturn(mockSubjects);

        Long subjectId = 1L;
        SubjectReadDTO mockSubject = new SubjectReadDTO();
        when(subjectService.getById(subjectId)).thenReturn(mockSubject);
    }
    @Test
    void testGetAllSubjects() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/subjects"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetSubjectById() throws Exception {
        Long subjectId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.get("/subjects/{id}", subjectId))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteSubjectById() throws Exception {
        Long subjectId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/subjects/{id}", subjectId))
                .andExpect(status().isOk());

        verify(subjectService, times(1)).deleteSubjectById(subjectId);
    }

    @Test
    void testSaveSubject() throws Exception {
        SubjectWriteDTO mockSubjectWriteDTO = new SubjectWriteDTO();
        mockSubjectWriteDTO.setName("tst");
        mockSubjectWriteDTO.setTeacherName("Kowalski");
        mockSubjectWriteDTO.setTeacherLastName("Test");

        String jsonContent = "{\"name\": \"tst\", \"teacherName\": \"Kowalski\", \"teacherLastName\": \"Test\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/subjects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isCreated());

        ArgumentCaptor<SubjectWriteDTO> argumentCaptor = ArgumentCaptor.forClass(SubjectWriteDTO.class);
        verify(subjectService, times(1)).saveSubject(argumentCaptor.capture());

        SubjectWriteDTO capturedSubject = argumentCaptor.getValue();
        assertEquals(mockSubjectWriteDTO.getName(), capturedSubject.getName());
        assertEquals(mockSubjectWriteDTO.getTeacherName(), capturedSubject.getTeacherName());
        assertEquals(mockSubjectWriteDTO.getTeacherLastName(), capturedSubject.getTeacherLastName());
    }

}