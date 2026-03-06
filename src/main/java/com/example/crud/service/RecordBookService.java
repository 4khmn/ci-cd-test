package com.example.crud.service;

import com.example.crud.dto.RecordBookDto;
import com.example.crud.mapper.RecordBookMapper;
import com.example.crud.repository.RecordBookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecordBookService {

    private final RecordBookRepo recordBookRepo;

    public RecordBookDto getById(Long id) {
        return RecordBookMapper.toDto(recordBookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("RecordBook not found")));
    }

    public List<RecordBookDto> getAll() {
        return recordBookRepo.findAll().stream()
                .map(RecordBookMapper::toDto)
                .collect(Collectors.toList());
    }
}
