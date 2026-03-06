package com.example.crud.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentDto {

    private Long id;

    private String name;

    private RecordBookDto recordBook;

    private List<CourseDto> courses;
}
