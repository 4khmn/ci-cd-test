package com.example.crud.dto;

import lombok.Data;

import java.util.List;

@Data
public class InstructorDto {

    private Long id;

    private String name;
    private List<CourseDto> courses;
}
