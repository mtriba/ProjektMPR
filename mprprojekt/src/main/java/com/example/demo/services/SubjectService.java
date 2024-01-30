package com.example.demo.services;
import com.example.demo.mapper.SubjectRead;
import com.example.demo.mapper.SubjectWrite;
import com.example.demo.model.Subject;
import com.example.demo.model.dto.SubjectReadDTO;
import com.example.demo.model.dto.SubjectWriteDTO;
import com.example.demo.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public List<SubjectReadDTO> getAll() {
        List<Subject> subjects = subjectRepository.findAll();
        return subjects.stream().map(SubjectRead::toDTO).toList();
    }

    public SubjectReadDTO getById(Long id) {
        Subject subject = subjectRepository.findById(id).orElse(null);
        return SubjectRead.toDTO(subject);
    }
    @Transactional
    public void deleteSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id).orElse(null);
        if (subject != null && subject.getStudents() == null) {
            subjectRepository.deleteById(id);
        } else {

            throw new IllegalArgumentException("Cannot delete subject with associated students");
        }
    }

    public void saveSubject(SubjectWriteDTO subjectWriteDTO) {
        subjectRepository.save(SubjectWrite.toEntity(subjectWriteDTO));
    }

}
