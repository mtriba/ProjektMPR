package com.example.demo.mapper;

import com.example.demo.model.Subject;
import com.example.demo.model.dto.SubjectReadDTO;

public class SubjectRead {

    public static SubjectReadDTO toDTO(Subject subject){
        SubjectReadDTO subjectReadDTO = new SubjectReadDTO();

        subjectReadDTO.setId(subject.getId());
        subjectReadDTO.setName(subject.getName());
        subjectReadDTO.setStudents(subject.getStudents());
        subjectReadDTO.setTeacherName(subject.getTeacherName());
        subjectReadDTO.setTeacherLastName(subject.getTeacherLastName());

        return subjectReadDTO;
    }
}
