package com.example.crud.service;

import com.example.crud.dto.CourseDto;
import com.example.crud.mapper.CourseMapper;
import com.example.crud.repository.CourseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepo courseRepo;

    public CourseDto getById(Long id) {
        return CourseMapper.toDto(courseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found")));
    }

    public List<CourseDto> getAll() {
        return courseRepo.findAll().stream()
                .map(CourseMapper::toDto)
                .collect(Collectors.toList());
    }
}
