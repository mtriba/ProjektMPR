package com.example.demo.services;
import com.example.demo.exceptions.InvalidValue;
import com.example.demo.mapper.StudentRead;
import com.example.demo.mapper.StudentWrite;
import com.example.demo.model.Student;
import com.example.demo.model.dto.StudentReadDTO;
import com.example.demo.model.dto.StudentWriteDTO;
import com.example.demo.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class StudentService {
    private final StudentRepository studentRepository;

    public List<StudentReadDTO> getAll(){
        List<Student> students = studentRepository.findAll();

        return students.stream().map(StudentRead::toDTO).toList();
    }

    public StudentReadDTO getById(Long id) {
        if (id <= 0) {
            throw new InvalidValue("Invalid value for id");
        }

        Student student = studentRepository.findById(id).orElse(null);

        if (student == null) {
            return null;
        }

        return StudentRead.toDTO(student);
    }

    @Transactional
    public void deleteStudentById(Long id){
        if(studentRepository.findById(id).stream().findFirst().orElse(null).getSubjects()!= null){
            studentRepository.deleteById(id);
        }
    }

    public void saveStudent(StudentWriteDTO studentWriteDto){
        studentRepository.save(StudentWrite.toEntity(studentWriteDto));
    }

    public List<StudentReadDTO> getWithAgeGreatherThan(Integer age){
        List<Student> students = studentRepository.findByAgeGreaterThan(age);
        return students.stream().map(StudentRead::toDTO).toList();
    }

    public Integer getStudentAge2timesById(Long id){
        Student student = studentRepository.findById(id).stream().findFirst().orElse(null);
        StudentReadDTO s = StudentRead.toDTO(student);
        return s.getAge()*2;
    }
    @Transactional
    public void deleteStudenWithAgeLessThan20(){
        studentRepository.deleteStudentsAgeLessThan20();
    }
}
