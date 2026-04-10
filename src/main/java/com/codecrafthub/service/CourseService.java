package com.codecrafthub.service;

import com.codecrafthub.model.Course;
import com.codecrafthub.model.CourseStatus;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Handles all file operations.
 */

@Service
@Slf4j
public class CourseService {

    private static final String FILE_NAME = "courses.json";

    private final ObjectMapper mapper;

    public CourseService() {

        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        createFileIfMissing();
    }

    /**
     * Create JSON file if it does not exist.
     */

    private void createFileIfMissing() {

        try {

            File file = new File(FILE_NAME);

            if (!file.exists()) {

                log.info("Creating courses.json file");

                mapper.writeValue(file, new ArrayList<Course>());
            }

        } catch (IOException e) {

            log.error("Failed to create file", e);
            throw new RuntimeException("File initialization error");
        }
    }

    /**
     * Read all courses from file.
     */

    private List<Course> readCourses() {

        try {

            return mapper.readValue(
                new File(FILE_NAME),
                new TypeReference<List<Course>>() {
                }
            );

        } catch (IOException e) {

            log.error("Error reading file", e);
            throw new RuntimeException("Failed to read courses file");
        }
    }

    /**
     * Write courses to file.
     */

    private void writeCourses(List<Course> courses) {

        try {

            mapper.writerWithDefaultPrettyPrinter()
                .writeValue(
                    new File(FILE_NAME),
                    courses
                );

        } catch (IOException e) {

            log.error("Error writing file", e);
            throw new RuntimeException("Failed to write courses file");
        }
    }

    /**
     * Generate next ID.
     */

    private Long generateNextId(List<Course> courses) {

        return courses.stream()
            .mapToLong(Course::getId)
            .max()
            .orElse(0) + 1;
    }

    /**
     * Validate required fields.
     */

    private void validateCourse(Course course) {

        if (course.getName() == null || course.getName().isBlank()) {
            throw new RuntimeException("Name is required");
        }

        if (course.getDescription() == null || course.getDescription().isBlank()) {
            throw new RuntimeException("Description is required");
        }

        if (course.getTargetDate() == null) {
            throw new RuntimeException("Target date is required");
        }

        if (course.getStatus() == null || !CourseStatus.isValid(course.getStatus())) {
            throw new RuntimeException(
                "Invalid status. Must be: Not Started, In Progress, or Completed"
            );
        }
    }

    /**
     * POST — Create course
     */

    public Course createCourse(Course course) {

        List<Course> courses = readCourses();

        validateCourse(course);

        course.setId(generateNextId(courses));
        course.setCreatedAt(LocalDateTime.now());

        courses.add(course);

        writeCourses(courses);

        log.info("Course created with id {}", course.getId());

        return course;
    }

    /**
     * GET — All courses
     */

    public List<Course> getAllCourses() {

        return readCourses();
    }

    /**
     * GET — Course by ID
     */

    public Course getCourseById(Long id) {

        return readCourses()
            .stream()
            .filter(c -> c.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    /**
     * PUT — Update course
     */

    public Course updateCourse(Long id, Course updatedCourse) {

        List<Course> courses = readCourses();

        Optional<Course> existing = courses
            .stream()
            .filter(c -> c.getId().equals(id))
            .findFirst();

        if (existing.isEmpty()) {
            throw new RuntimeException("Course not found");
        }

        validateCourse(updatedCourse);

        Course course = existing.get();

        course.setName(updatedCourse.getName());
        course.setDescription(updatedCourse.getDescription());
        course.setTargetDate(updatedCourse.getTargetDate());
        course.setStatus(updatedCourse.getStatus());

        writeCourses(courses);

        log.info("Course updated with id {}", id);

        return course;
    }

    /**
     * DELETE — Remove course
     */

    public void deleteCourse(Long id) {

        List<Course> courses = readCourses();

        boolean removed = courses.removeIf(
            c -> c.getId().equals(id)
        );

        if (!removed) {
            throw new RuntimeException("Course not found");
        }

        writeCourses(courses);

        log.info("Course deleted with id {}", id);
    }
}