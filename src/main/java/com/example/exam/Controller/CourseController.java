package com.example.exam.Controller;

import com.example.exam.Entity.Course;
import com.example.exam.Service.CourseService;
import com.example.exam.utils.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    // Get all courses
    @GetMapping("/get")
    public ResponseEntity<Object> getAllCourses() {
        try {
            List<Course> courses = courseService.getAllCourses();
            return ResponseHandler.generateResponse("Courses retrieved successfully", HttpStatus.OK, courses);
        } catch (Exception e) {
            logger.error("Error retrieving all courses", e);
            return ResponseHandler.generateErrorResponse("Failed to retrieve courses", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create a new course
    @PostMapping("/create")
    public ResponseEntity<Object> createCourse(@RequestBody Course course) {
        try {
            Course createdCourse = courseService.createCourse(course);
            return ResponseHandler.generateResponse("Course created successfully", HttpStatus.CREATED, createdCourse);
        } catch (Exception e) {
            logger.error("Error creating course", e);
            return ResponseHandler.generateErrorResponse("Failed to create course", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get course by ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCourseById(@PathVariable Long id) {
        try {
            Optional<Course> course = courseService.getCourseById(id);
            return course
                    .map(value -> ResponseHandler.generateResponse("Course found", HttpStatus.OK, value))
                    .orElseGet(() -> ResponseHandler.generateNotFoundResponse("Course not found"));
        } catch (Exception e) {
            logger.error("Error retrieving course with ID: " + id, e);
            return ResponseHandler.generateErrorResponse("Failed to retrieve course", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update course by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateCourse(@PathVariable Long id, @RequestBody Course courseDetails) {
        try {
            Course updatedCourse = courseService.updateCourse(id, courseDetails);
            return updatedCourse == null
                    ? ResponseHandler.generateNotFoundResponse("Course not found")
                    : ResponseHandler.generateResponse("Course updated successfully", HttpStatus.OK, updatedCourse);
        } catch (Exception e) {
            logger.error("Error updating course with ID: " + id, e);
            return ResponseHandler.generateErrorResponse("Failed to update course", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete course by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCourse(@PathVariable Long id) {
        try {
            boolean deleted = courseService.deleteCourseById(id);
            return deleted
                    ? ResponseHandler.generateResponse("Course deleted successfully", HttpStatus.NO_CONTENT, null)
                    : ResponseHandler.generateNotFoundResponse("Course not found");
        } catch (Exception e) {
            logger.error("Error deleting course with ID: " + id, e);
            return ResponseHandler.generateErrorResponse("Failed to delete course", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
