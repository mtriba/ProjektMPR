package com.example.demo.mapper;

import com.example.demo.model.Subject;
import com.example.demo.model.dto.SubjectReadDTO;
import com.example.demo.model.dto.SubjectWriteDTO;

public class SubjectWrite {

public static Subject toEntity(SubjectWriteDTO subjectWriteDTO){
    Subject subject = new Subject();

    subject.setName(subjectWriteDTO.getName());
    subject.setStudents(subjectWriteDTO.getStudents());
    subject.setTeacherName(subjectWriteDTO.getTeacherName());
    subject.setTeacherLastName(subjectWriteDTO.getTeacherLastName());

    return subject;
    }
}
