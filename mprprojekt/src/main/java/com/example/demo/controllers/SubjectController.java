package com.example.demo.controllers;
import com.example.demo.model.dto.SubjectReadDTO;
import com.example.demo.model.dto.SubjectWriteDTO;
import com.example.demo.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<SubjectReadDTO>> getAllSubjects() {
        List<SubjectReadDTO> subjects = subjectService.getAll();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectReadDTO> getSubjectById(@PathVariable Long id) {
        SubjectReadDTO subject = subjectService.getById(id);
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubjectById(@PathVariable Long id) {
        subjectService.deleteSubjectById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> saveSubject(@RequestBody SubjectWriteDTO subjectWriteDTO) {
        subjectService.saveSubject(subjectWriteDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
