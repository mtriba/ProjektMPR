package com.example.demo.model.dto;

import com.example.demo.model.Student;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectWriteDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String teacherName;
    @NotBlank
    private String teacherLastName;
    private List<Student> students;
}
