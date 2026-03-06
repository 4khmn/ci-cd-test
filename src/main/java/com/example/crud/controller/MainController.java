package com.example.crud.controller;

import com.example.crud.dto.*;
import com.example.crud.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MainController {

    private final CourseService courseService;
    private final StudentService studentService;
    private final InstructorService instructorService;
    private final LessonService lessonService;
    private final RecordBookService recordBookService;

    @GetMapping("/courses")
    public List<CourseDto> getAllCourses() {
        log.info("GET /courses - fetching all courses");
        return courseService.getAll();
    }

    @GetMapping("/courses/{id}")
    public CourseDto getCourseById(@PathVariable Long id) {
        log.info("meow");
        return courseService.getById(id);
    }

    @GetMapping("/students")
    public List<StudentDto> getAllStudents() {
        return studentService.getAll();
    }

    @GetMapping("/students/{id}")
    public StudentDto getStudentById(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @GetMapping("/instructors")
    public List<InstructorDto> getAllInstructors() {
        return instructorService.getAll();
    }

    @GetMapping("/instructors/{id}")
    public InstructorDto getInstructorById(@PathVariable Long id) {
        return instructorService.getById(id);
    }

    @GetMapping("/lessons")
    public List<LessonDto> getAllLessons() {
        return lessonService.getAll();
    }

    @GetMapping("/lessons/{id}")
    public LessonDto getLessonById(@PathVariable Long id) {
        return lessonService.getById(id);
    }

    @GetMapping("/record-books")
    public List<RecordBookDto> getAllRecordBooks() {
        return recordBookService.getAll();
    }

    @GetMapping("/record-books/{id}")
    public RecordBookDto getRecordBookById(@PathVariable Long id) {
        return recordBookService.getById(id);
    }
}
