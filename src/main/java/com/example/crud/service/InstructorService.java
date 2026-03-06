package com.example.crud.service;

import com.example.crud.dto.InstructorDto;
import com.example.crud.mapper.InstructorMapper;
import com.example.crud.repository.InstructorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepo instructorRepo;

    public InstructorDto getById(Long id) {
        return InstructorMapper.toDto(instructorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found")));
    }

    public List<InstructorDto> getAll() {
        return instructorRepo.findAll().stream()
                .map(InstructorMapper::toDto)
                .collect(Collectors.toList());
    }
}
