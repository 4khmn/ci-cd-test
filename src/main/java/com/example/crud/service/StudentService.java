package com.example.crud.service;

import com.example.crud.dto.StudentDto;
import com.example.crud.mapper.StudentMapper;
import com.example.crud.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepo studentRepo;

    public StudentDto getById(Long id) {
        return StudentMapper.toDto(studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found")));
    }

    public List<StudentDto> getAll() {
        return studentRepo.findAll().stream()
                .map(StudentMapper::toDto)
                .collect(Collectors.toList());
    }
}
