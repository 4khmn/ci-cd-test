package com.example.crud.service;

import com.example.crud.dto.LessonDto;
import com.example.crud.mapper.LessonMapper;
import com.example.crud.repository.LessonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepo lessonRepo;

    public LessonDto getById(Long id) {
        return LessonMapper.toDto(lessonRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found")));
    }

    public List<LessonDto> getAll() {
        return lessonRepo.findAll().stream()
                .map(LessonMapper::toDto)
                .collect(Collectors.toList());
    }
}
