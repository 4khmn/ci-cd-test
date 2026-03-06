package com.example.crud.dto;

import lombok.Data;

import java.util.List;

@Data
public class CourseDto {

    private Long id;

    private String name;

    private Long instructorId;

    private List<LessonDto> lessons;

    private List<StudentDto> students;
}
