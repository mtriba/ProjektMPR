package com.example.demo;
import com.example.demo.controllers.SubjectController;
import com.example.demo.model.dto.SubjectReadDTO;
import com.example.demo.model.dto.SubjectWriteDTO;
import com.example.demo.services.SubjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SubjectControllerTests {

    @Mock
    private SubjectService subjectService;

    @InjectMocks
    private SubjectController subjectController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllSubjects() {
        List<SubjectReadDTO> mockSubjects = Collections.singletonList(new SubjectReadDTO());
        when(subjectService.getAll()).thenReturn(mockSubjects);

        ResponseEntity<List<SubjectReadDTO>> response = subjectController.getAllSubjects();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockSubjects, response.getBody());
        verify(subjectService, times(1)).getAll();
    }

    @Test
    void testGetSubjectById() {
        Long subjectId = 1L;
        SubjectReadDTO mockSubject = new SubjectReadDTO();
        when(subjectService.getById(subjectId)).thenReturn(mockSubject);

        ResponseEntity<SubjectReadDTO> response = subjectController.getSubjectById(subjectId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockSubject, response.getBody());
        verify(subjectService, times(1)).getById(subjectId);
    }

    @Test
    void testDeleteSubjectById() {
        Long subjectId = 1L;

        ResponseEntity<Void> response = subjectController.deleteSubjectById(subjectId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(subjectService, times(1)).deleteSubjectById(subjectId);
    }

    @Test
    void testSaveSubject() {
        SubjectWriteDTO mockSubjectWriteDTO = new SubjectWriteDTO();

        ResponseEntity<Void> response = subjectController.saveSubject(mockSubjectWriteDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(subjectService, times(1)).saveSubject(mockSubjectWriteDTO);
    }
}