package com.example.crud.mapper;

import com.example.crud.dto.CourseDto;
import com.example.crud.dto.LessonDto;
import com.example.crud.dto.StudentDto;
import com.example.crud.entity.Course;
import com.example.crud.entity.Lesson;
import com.example.crud.entity.Student;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class CourseMapper {

    private CourseMapper() {
    }

    public static CourseDto toDto(Course course) {
        if (course == null) {
            return null;
        }

        CourseDto dto = new CourseDto();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setInstructorId(course.getInstructor() != null ? course.getInstructor().getId() : null);

        dto.setLessons(toLessonDtoList(course.getLesson()));
        dto.setStudents(toStudentShortDtoList(course.getStudents()));

        return dto;
    }

    private static List<LessonDto> toLessonDtoList(List<Lesson> lessons) {
        if (lessons == null || lessons.isEmpty()) {
            return Collections.emptyList();
        }
        return lessons.stream()
                .map(CourseMapper::toLessonDto)
                .collect(Collectors.toList());
    }

    private static LessonDto toLessonDto(Lesson lesson) {
        if (lesson == null) {
            return null;
        }
        LessonDto dto = new LessonDto();
        dto.setId(lesson.getId());
        dto.setName(lesson.getName());
        dto.setCourse_id(lesson.getCourse() != null ? lesson.getCourse().getId() : null);
        return dto;
    }

    /**
     * Упрощённое представление студента внутри курса без его курсов,
     * чтобы избежать бесконечной рекурсии в DTO.
     */
    private static List<StudentDto> toStudentShortDtoList(List<Student> students) {
        if (students == null || students.isEmpty()) {
            return Collections.emptyList();
        }
        return students.stream()
                .map(CourseMapper::toStudentShortDto)
                .collect(Collectors.toList());
    }

    private static StudentDto toStudentShortDto(Student student) {
        if (student == null) {
            return null;
        }
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setName(student.getName());
        // без recordBook и courses
        return dto;
    }
}
