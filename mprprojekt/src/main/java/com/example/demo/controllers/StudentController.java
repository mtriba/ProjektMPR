package com.example.demo.controllers;

import com.example.demo.model.dto.StudentReadDTO;
import com.example.demo.model.dto.StudentWriteDTO;
import com.example.demo.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentReadDTO>> getAllStudents() {
        List<StudentReadDTO> students = studentService.getAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentReadDTO> getStudentById(@PathVariable Long id) {
        StudentReadDTO student = studentService.getById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> saveStudent(@RequestBody StudentWriteDTO studentWriteDto) {
        studentService.saveStudent(studentWriteDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/ageGreaterThan")
    public ResponseEntity<List<StudentReadDTO>> getStudentsWithAgeGreaterThan(@RequestParam Integer age) {
        List<StudentReadDTO> students = studentService.getWithAgeGreatherThan(age);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/age2times/{id}")
    public ResponseEntity<Integer> getStudentAge2timesById(@PathVariable Long id) {
        Integer age2times = studentService.getStudentAge2timesById(id);
        return new ResponseEntity<>(age2times, HttpStatus.OK);
    }
}
