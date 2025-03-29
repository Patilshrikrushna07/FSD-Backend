package com.example.exam.Controller;

import com.example.exam.Entity.Student;
import com.example.exam.Service.StudentService;
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
@RequestMapping("/api/students")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    // Get all students
    @GetMapping("/get")
    public ResponseEntity<Object> getAllStudents() {
        try {
            List<Student> students = studentService.getAllStudents();
            return ResponseHandler.generateResponse("Students retrieved successfully", HttpStatus.OK, students);
        } catch (Exception e) {
            logger.error("Error retrieving all students", e);
            return ResponseHandler.generateErrorResponse("Failed to retrieve students", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create a new student
    @PostMapping("/create")
    public ResponseEntity<Object> createStudent(@RequestBody Student student) {
        try {
            Student createdStudent = studentService.createStudent(student);
            return ResponseHandler.generateResponse("Student created successfully", HttpStatus.CREATED, createdStudent);
        } catch (Exception e) {
            logger.error("Error creating student", e);
            return ResponseHandler.generateErrorResponse("Failed to create student", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudentById(@PathVariable Long id) {
        try {
            Optional<Student> student = studentService.getStudentById(id);
            return student
                    .map(value -> ResponseHandler.generateResponse("Student found", HttpStatus.OK, value))
                    .orElseGet(() -> ResponseHandler.generateNotFoundResponse("Student not found"));
        } catch (Exception e) {
            logger.error("Error retrieving student with ID: " + id, e);
            return ResponseHandler.generateErrorResponse("Failed to retrieve student", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update student by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        try {
            Student updatedStudent = studentService.updateStudent(id, studentDetails);
            return updatedStudent == null
                    ? ResponseHandler.generateNotFoundResponse("Student not found")
                    : ResponseHandler.generateResponse("Student updated successfully", HttpStatus.OK, updatedStudent);
        } catch (Exception e) {
            logger.error("Error updating student with ID: " + id, e);
            return ResponseHandler.generateErrorResponse("Failed to update student", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Partially update a student by ID
    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> partiallyUpdateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        try {
            Student updatedStudent = studentService.partiallyUpdateStudent(id, studentDetails);
            return updatedStudent == null
                    ? ResponseHandler.generateNotFoundResponse("Student not found")
                    : ResponseHandler.generateResponse("Student partially updated successfully", HttpStatus.OK, updatedStudent);
        } catch (Exception e) {
            logger.error("Error partially updating student with ID: " + id, e);
            return ResponseHandler.generateErrorResponse("Failed to partially update student", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete a student by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable Long id) {
        try {
            boolean deleted = studentService.deleteStudentById(id);
            return deleted
                    ? ResponseHandler.generateResponse("Student deleted successfully", HttpStatus.NO_CONTENT, null)
                    : ResponseHandler.generateNotFoundResponse("Student not found");
        } catch (Exception e) {
            logger.error("Error deleting student with ID: " + id, e);
            return ResponseHandler.generateErrorResponse("Failed to delete student", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
