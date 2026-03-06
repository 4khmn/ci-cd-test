package com.example.crud.mapper;

import com.example.crud.dto.LessonDto;
import com.example.crud.entity.Lesson;

public final class LessonMapper {

    private LessonMapper() {
    }

    public static LessonDto toDto(Lesson lesson) {
        if (lesson == null) {
            return null;
        }

        LessonDto dto = new LessonDto();
        dto.setId(lesson.getId());
        dto.setName(lesson.getName());
        dto.setCourse_id(lesson.getCourse() != null ? lesson.getCourse().getId() : null);

        return dto;
    }
}

