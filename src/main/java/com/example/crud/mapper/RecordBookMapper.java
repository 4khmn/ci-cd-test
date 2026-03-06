package com.example.crud.mapper;

import com.example.crud.dto.RecordBookDto;
import com.example.crud.entity.RecordBook;

public final class RecordBookMapper {

    private RecordBookMapper() {
    }

    public static RecordBookDto toDto(RecordBook recordBook) {
        if (recordBook == null) {
            return null;
        }

        RecordBookDto dto = new RecordBookDto();
        dto.setId(recordBook.getId());
        dto.setCode(recordBook.getCode());
        dto.setStudent_id(recordBook.getStudent() != null ? recordBook.getStudent().getId() : null);

        return dto;
    }
}

