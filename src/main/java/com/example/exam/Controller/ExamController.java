package com.example.exam.Controller;

import com.example.exam.Entity.Exam;
import com.example.exam.Service.ExamService;
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
@RequestMapping("/api/exams")
public class ExamController {

    private static final Logger logger = LoggerFactory.getLogger(ExamController.class);

    @Autowired
    private ExamService examService;

    // Get all exams
    @GetMapping("/get")
    public ResponseEntity<Object> getAllExams() {
        try {
            List<Exam> exams = examService.getAllExams();
            return ResponseHandler.generateResponse("Exams retrieved successfully", HttpStatus.OK, exams);
        } catch (Exception e) {
            logger.error("Error retrieving all exams", e);
            return ResponseHandler.generateErrorResponse("Failed to retrieve exams", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create a new exam
    @PostMapping("/create")
    public ResponseEntity<Object> createExam(@RequestBody Exam exam) {
        try {
            Exam createdExam = examService.createExam(exam);
            return ResponseHandler.generateResponse("Exam created successfully", HttpStatus.CREATED, createdExam);
        } catch (Exception e) {
            logger.error("Error creating exam", e);
            return ResponseHandler.generateErrorResponse("Failed to create exam", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get exam by ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getExamById(@PathVariable Long id) {
        try {
            Optional<Exam> exam = examService.getExamById(id);
            if (exam.isPresent()) {
                return ResponseHandler.generateResponse("Exam found", HttpStatus.OK, exam.get());
            } else {
                return ResponseHandler.generateNotFoundResponse("Exam not found");
            }
        } catch (Exception e) {
            logger.error("Error retrieving exam with ID: " + id, e);
            return ResponseHandler.generateErrorResponse("Failed to retrieve exam", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update exam by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateExam(@PathVariable Long id, @RequestBody Exam examDetails) {
        try {
            Exam updatedExam = examService.updateExam(id, examDetails);
            if (updatedExam != null) {
                return ResponseHandler.generateResponse("Exam updated successfully", HttpStatus.OK, updatedExam);
            } else {
                return ResponseHandler.generateNotFoundResponse("Exam not found");
            }
        } catch (Exception e) {
            logger.error("Error updating exam with ID: " + id, e);
            return ResponseHandler.generateErrorResponse("Failed to update exam", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete exam by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteExam(@PathVariable Long id) {
        try {
            boolean deleted = examService.deleteExamById(id);
            if (deleted) {
                return ResponseHandler.generateResponse("Exam deleted successfully", HttpStatus.NO_CONTENT, null);
            } else {
                return ResponseHandler.generateNotFoundResponse("Exam not found");
            }
        } catch (Exception e) {
            logger.error("Error deleting exam with ID: " + id, e);
            return ResponseHandler.generateErrorResponse("Failed to delete exam", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
