package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Subject {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String teacherName;
    private String teacherLastName;
    @ManyToMany(mappedBy = "subjects")
    private List<Student> students;
}
