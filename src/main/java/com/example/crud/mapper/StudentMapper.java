package com.example.crud.mapper;

import com.example.crud.dto.CourseDto;
import com.example.crud.dto.RecordBookDto;
import com.example.crud.dto.StudentDto;
import com.example.crud.entity.Course;
import com.example.crud.entity.Student;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class StudentMapper {

    private StudentMapper() {
    }

    public static StudentDto toDto(Student student) {
        if (student == null) {
            return null;
        }

        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setName(student.getName());

        RecordBookDto recordBookDto = RecordBookMapper.toDto(student.getRecordBook());
        dto.setRecordBook(recordBookDto);

        dto.setCourses(toCourseShortDtoList(student.getCourses()));

        return dto;
    }

    private static List<CourseDto> toCourseShortDtoList(List<Course> courses) {
        if (courses == null || courses.isEmpty()) {
            return Collections.emptyList();
        }
        return courses.stream()
                .map(StudentMapper::toCourseShortDto)
                .collect(Collectors.toList());
    }

    /**
     * Упрощённое представление курса внутри студента без студентов и уроков,
     * чтобы избежать рекурсивных ссылок.
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

