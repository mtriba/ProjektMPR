package com.example.demo;
import com.example.demo.model.Subject;
import com.example.demo.model.dto.SubjectReadDTO;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.services.SubjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SubjectServiceTests {

    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private SubjectService subjectService;
    @BeforeEach
    public void clearedDataBase(){
        subjectRepository.deleteAll();
    }

    @Test
    public void testFindAllSubjects() {

        Subject subject1 = new Subject();
        subject1.setName("Math");

        Subject subject2 = new Subject();
        subject2.setName("History");

        Mockito.when(subjectRepository.findAll()).thenReturn(Arrays.asList(subject1, subject2));

        List<SubjectReadDTO> subjects = subjectService.getAll();

        assertEquals(2, subjects.size());
    }

    @Test
    public void testFindSubjectById() {
        // Given
        Long subjectId = 1L;
        Subject subject = new Subject();
        subject.setId(subjectId);
        subject.setName("Math");

        Mockito.when(subjectRepository.findById(subjectId)).thenReturn(Optional.of(subject));

        SubjectReadDTO result = subjectService.getById(subjectId);

        assertEquals("Math", result.getName());
    }
}
