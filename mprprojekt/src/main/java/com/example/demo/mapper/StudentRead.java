package com.example.demo.mapper;

import com.example.demo.model.Student;
import com.example.demo.model.dto.StudentReadDTO;

public class StudentRead {

        public static StudentReadDTO toDTO(Student student) {
                if (student == null) {
                        return null;
                }

                StudentReadDTO studentReadDTO = new StudentReadDTO();

                studentReadDTO.setId(student.getId());
                studentReadDTO.setStudentIndex(student.getStudentIndex());
                studentReadDTO.setAge(student.getAge());
                studentReadDTO.setName(student.getName());
                studentReadDTO.setLastName(student.getLastName());
                studentReadDTO.setSubjects(student.getSubjects());

                return studentReadDTO;
        }
}
