package com.example.demo;

import com.example.demo.model.Subject;
import com.example.demo.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class SubjectRepositoryTests {

    @Autowired
    private SubjectRepository subjectRepository;

    @Test
    public void testSaveAndRetrieveSubject() {

        Subject subject = new Subject();
        subject.setName("Math");
        subject.setTeacherName("Mr. Smith");

        Subject savedSubject = subjectRepository.save(subject);
        Subject retrievedSubject = subjectRepository.findById(savedSubject.getId()).orElse(null);

        assertTrue(retrievedSubject != null);
        assertEquals("Math", retrievedSubject.getName());
        assertEquals("Mr. Smith", retrievedSubject.getTeacherName());
    }

}
