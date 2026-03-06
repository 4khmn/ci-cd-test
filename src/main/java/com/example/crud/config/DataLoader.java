package com.example.crud.config;

import com.example.crud.entity.*;
import com.example.crud.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Заполняет БД большим количеством тестовых данных.
 * Запускается при старте приложения с профилем "seed".
 * Использование: mvn spring-boot:run -Dspring-boot.run.profiles=seed
 */
@Component
@Profile("seed")
@Slf4j
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final InstructorRepo instructorRepo;
    private final StudentRepo studentRepo;
    private final RecordBookRepo recordBookRepo;
    private final CourseRepo courseRepo;
    private final LessonRepo lessonRepo;

    private static final int INSTRUCTORS_COUNT = 150;
    private static final int STUDENTS_COUNT = 800;
    private static final int COURSES_COUNT = 300;
    private static final int LESSONS_PER_COURSE = 5;
    private static final int COURSES_PER_STUDENT_MIN = 2;
    private static final int COURSES_PER_STUDENT_MAX = 8;

    private final Random random = new Random(42);

    @Override
    @Transactional
    public void run(String... args) {
        if (instructorRepo.count() > 0) {
            log.info("БД уже содержит данные, пропуск загрузки");
            return;
        }

        log.info("Начинаем загрузку тестовых данных...");

        List<Instructor> instructors = createInstructors();
        List<Student> students = createStudents();
        createRecordBooks(students);
        List<Course> courses = createCourses(instructors);
        createLessons(courses);
        linkStudentsToCourses(students, courses);

        log.info("Загрузка завершена: {} instructors, {} students, {} courses, {} lessons",
                instructors.size(), students.size(), courses.size(),
                courses.size() * LESSONS_PER_COURSE);
    }

    private List<Instructor> createInstructors() {
        List<Instructor> list = new ArrayList<>();
        for (int i = 1; i <= INSTRUCTORS_COUNT; i++) {
            Instructor instructor = new Instructor();
            instructor.setName("Преподаватель " + i);
            list.add(instructorRepo.save(instructor));
        }
        return list;
    }

    private List<Student> createStudents() {
        List<Student> list = new ArrayList<>();
        for (int i = 1; i <= STUDENTS_COUNT; i++) {
            Student student = new Student();
            student.setName("Студент " + i);
            student.setCourses(new ArrayList<>());
            list.add(studentRepo.save(student));
        }
        return list;
    }

    private void createRecordBooks(List<Student> students) {
        for (int i = 0; i < students.size(); i++) {
            RecordBook rb = new RecordBook();
            rb.setCode("RB-" + String.format("%06d", i + 1));
            rb.setStudent(students.get(i));
            recordBookRepo.save(rb);
            students.get(i).setRecordBook(rb);
            studentRepo.save(students.get(i));
        }
    }

    private List<Course> createCourses(List<Instructor> instructors) {
        List<Course> list = new ArrayList<>();
        for (int i = 1; i <= COURSES_COUNT; i++) {
            Course course = new Course();
            course.setName("Курс " + i);
            course.setInstructor(instructors.get(random.nextInt(instructors.size())));
            course.setLesson(new ArrayList<>());
            course.setStudents(new ArrayList<>());
            list.add(courseRepo.save(course));
        }
        return list;
    }

    private void createLessons(List<Course> courses) {
        String[] topics = {"Введение", "Основы", "Практика", "Углублённо", "Итоги"};
        for (Course course : courses) {
            for (int i = 0; i < LESSONS_PER_COURSE; i++) {
                Lesson lesson = new Lesson();
                lesson.setName(course.getName() + " - " + topics[i]);
                lesson.setCourse(course);
                lessonRepo.save(lesson);
            }
        }
    }

    private void linkStudentsToCourses(List<Student> students, List<Course> courses) {
        for (Student student : students) {
            int count = COURSES_PER_STUDENT_MIN + random.nextInt(COURSES_PER_STUDENT_MAX - COURSES_PER_STUDENT_MIN + 1);
            List<Course> studentCourses = new ArrayList<>(student.getCourses());
            for (int i = 0; i < count; i++) {
                Course course = courses.get(random.nextInt(courses.size()));
                if (!studentCourses.contains(course)) {
                    studentCourses.add(course);
                }
            }
            student.setCourses(studentCourses);
            studentRepo.save(student);
        }
    }
}
