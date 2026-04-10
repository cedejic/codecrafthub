package com.codecrafthub.controller;

import com.codecrafthub.model.Course;
import com.codecrafthub.service.CourseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST Controller exposing CRUD endpoints.
 */

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@Slf4j
public class CourseController {

    private final CourseService courseService;

    /**
     * POST /api/courses
     */

    @PostMapping
    public ResponseEntity<Course> createCourse(
        @RequestBody Course course
    ) {

        log.info("Creating new course");

        return ResponseEntity.ok(
            courseService.createCourse(course)
        );
    }

    /**
     * GET /api/courses
     */

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {

        return ResponseEntity.ok(
            courseService.getAllCourses()
        );
    }

    /**
     * GET /api/courses/{id}
     */

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(
        @PathVariable Long id
    ) {

        return ResponseEntity.ok(
            courseService.getCourseById(id)
        );
    }

    /**
     * PUT /api/courses/{id}
     */

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(
        @PathVariable Long id,
        @RequestBody Course course
    ) {

        return ResponseEntity.ok(
            courseService.updateCourse(id, course)
        );
    }

    /**
     * DELETE /api/courses/{id}
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(
        @PathVariable Long id
    ) {

        courseService.deleteCourse(id);

        return ResponseEntity.ok(
            "Course deleted successfully"
        );
    }
    /**
     * GET /api/courses/stats
     *
     * Returns statistics about courses:
     * - Total number of courses
     * - Number of courses by status
     *
     * Example response:
     *
     * {
     *   "total_courses": 5,
     *   "by_status": {
     *     "Not Started": 2,
     *     "In Progress": 2,
     *     "Completed": 1
     *   }
     * }
     */

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getCourseStats() {

        log.info("Fetching course statistics");

        List<Course> courses = courseService.getAllCourses();

        long totalCourses = courses.size();

        long notStarted = courses.stream()
            .filter(c -> "Not Started".equals(c.getStatus()))
            .count();

        long inProgress = courses.stream()
            .filter(c -> "In Progress".equals(c.getStatus()))
            .count();

        long completed = courses.stream()
            .filter(c -> "Completed".equals(c.getStatus()))
            .count();

        Map<String, Long> byStatus = new HashMap<>();
        byStatus.put("Not Started", notStarted);
        byStatus.put("In Progress", inProgress);
        byStatus.put("Completed", completed);

        Map<String, Object> stats = new HashMap<>();
        stats.put("total_courses", totalCourses);
        stats.put("by_status", byStatus);

        return ResponseEntity.ok(stats);
    }
}