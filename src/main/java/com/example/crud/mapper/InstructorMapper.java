package com.example.crud.mapper;

import com.example.crud.dto.CourseDto;
import com.example.crud.dto.InstructorDto;
import com.example.crud.entity.Course;
import com.example.crud.entity.Instructor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class InstructorMapper {

    private InstructorMapper() {
    }

    public static InstructorDto toDto(Instructor instructor) {
        if (instructor == null) {
            return null;
        }

        InstructorDto dto = new InstructorDto();
        dto.setId(instructor.getId());
        dto.setName(instructor.getName());

        dto.setCourses(toCourseShortDtoList(instructor.getCourses()));

        return dto;
    }

    private static List<CourseDto> toCourseShortDtoList(List<Course> courses) {
        if (courses == null || courses.isEmpty()) {
            return Collections.emptyList();
        }
        return courses.stream()
                .map(InstructorMapper::toCourseShortDto)
                .collect(Collectors.toList());
    }

    /**
     * Упрощённое представление курса внутри преподавателя без студентов и уроков.
     */
    private static CourseDto toCourseShortDto(Course course) {
        if (course == null) {
            return null;
        }
        CourseDto dto = new CourseDto();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setInstructorId(course.getInstructor() != null ? course.getInstructor().getId() : null);
        return dto;
    }
}

