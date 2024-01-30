package com.example.demo.model.dto;

import com.example.demo.model.Subject;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentWriteDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @NotNull
    private Integer age;
    @NotBlank
    private String studentIndex;
    private List<Subject> subjects;
}