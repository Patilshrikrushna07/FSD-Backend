package com.example.exam.Controller;

import com.example.exam.Entity.Teacher;
import com.example.exam.Service.TeacherService;
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
@RequestMapping("/api/teachers")
public class TeacherController {

    private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private TeacherService teacherService;

    // Get all teachers
    @GetMapping("/get")
    public ResponseEntity<Object> getAllTeachers() {
        try {
            List<Teacher> teachers = teacherService.getAllTeachers();
            return ResponseHandler.generateResponse("Teachers retrieved successfully", HttpStatus.OK, teachers);
        } catch (Exception e) {
            logger.error("Error retrieving all teachers", e);
            return ResponseHandler.generateErrorResponse("Failed to retrieve teachers", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create a new teacher
    @PostMapping("/create")
    public ResponseEntity<Object> createTeacher(@RequestBody Teacher teacher) {
        try {
            Teacher createdTeacher = teacherService.createTeacher(teacher);
            return ResponseHandler.generateResponse("Teacher created successfully", HttpStatus.CREATED, createdTeacher);
        } catch (Exception e) {
            logger.error("Error creating teacher", e);
            return ResponseHandler.generateErrorResponse("Failed to create teacher", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get teacher by ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getTeacherById(@PathVariable Long id) {
        try {
            Optional<Teacher> teacher = teacherService.getTeacherById(id);
            return teacher
                    .map(value -> ResponseHandler.generateResponse("Teacher found", HttpStatus.OK, value))
                    .orElseGet(() -> ResponseHandler.generateNotFoundResponse("Teacher not found"));
        } catch (Exception e) {
            logger.error("Error retrieving teacher with ID: " + id, e);
            return ResponseHandler.generateErrorResponse("Failed to retrieve teacher", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update teacher by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacherDetails) {
        try {
            Teacher updatedTeacher = teacherService.updateTeacher(id, teacherDetails);
            return updatedTeacher == null
                    ? ResponseHandler.generateNotFoundResponse("Teacher not found")
                    : ResponseHandler.generateResponse("Teacher updated successfully", HttpStatus.OK, updatedTeacher);
        } catch (Exception e) {
            logger.error("Error updating teacher with ID: " + id, e);
            return ResponseHandler.generateErrorResponse("Failed to update teacher", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete teacher by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteTeacher(@PathVariable Long id) {
        try {
            boolean deleted = teacherService.deleteTeacherById(id);
            return deleted
                    ? ResponseHandler.generateResponse("Teacher deleted successfully", HttpStatus.NO_CONTENT, null)
                    : ResponseHandler.generateNotFoundResponse("Teacher not found");
        } catch (Exception e) {
            logger.error("Error deleting teacher with ID: " + id, e);
            return ResponseHandler.generateErrorResponse("Failed to delete teacher", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
