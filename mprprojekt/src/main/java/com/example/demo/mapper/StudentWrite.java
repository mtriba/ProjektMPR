package com.example.demo.mapper;

import com.example.demo.model.Student;
import com.example.demo.model.dto.StudentWriteDTO;

public class StudentWrite {

    public static Student toEntity(StudentWriteDTO studentWriteDTO){
        Student student = new Student();

        student.setStudentIndex(studentWriteDTO.getStudentIndex());
        student.setAge(studentWriteDTO.getAge());
        student.setName(studentWriteDTO.getName());
        student.setSubjects(studentWriteDTO.getSubjects());
        student.setLastName(studentWriteDTO.getLastName());

        return student;
    }

}
