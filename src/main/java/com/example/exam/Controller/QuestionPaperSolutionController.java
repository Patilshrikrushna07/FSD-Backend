package com.example.exam.Controller;

import com.example.exam.Entity.QuestionPaperSolution;
import com.example.exam.Service.QuestionPaperSolutionService;
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
@RequestMapping("/api/questionpapersolutions")
public class QuestionPaperSolutionController {

    private static final Logger logger = LoggerFactory.getLogger(QuestionPaperSolutionController.class);

    @Autowired
    private QuestionPaperSolutionService questionPaperSolutionService;

    // Get all question paper solutions
    @GetMapping("/get")
    public ResponseEntity<Object> getAllQuestionPaperSolutions() {
        try {
            List<QuestionPaperSolution> solutions = questionPaperSolutionService.getAllQuestionPaperSolutions();
            return ResponseHandler.generateResponse("Solutions retrieved successfully", HttpStatus.OK, solutions);
        } catch (Exception e) {
            logger.error("Error retrieving all question paper solutions", e);
            return ResponseHandler.generateErrorResponse("Failed to retrieve solutions", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create a new question paper solution
    @PostMapping("/create")
    public ResponseEntity<Object> createQuestionPaperSolution(@RequestBody QuestionPaperSolution solution) {
        try {
            QuestionPaperSolution createdSolution = questionPaperSolutionService.createQuestionPaperSolution(solution);
            return ResponseHandler.generateResponse("Solution created successfully", HttpStatus.CREATED, createdSolution);
        } catch (Exception e) {
            logger.error("Error creating question paper solution", e);
            return ResponseHandler.generateErrorResponse("Failed to create solution", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get question paper solution by ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getQuestionPaperSolutionById(@PathVariable Long id) {
        try {
            Optional<QuestionPaperSolution> solution = questionPaperSolutionService.getQuestionPaperSolutionById(id);
            return solution
                    .map(value -> ResponseHandler.generateResponse("Solution found", HttpStatus.OK, value))
                    .orElseGet(() -> ResponseHandler.generateNotFoundResponse("Solution not found"));
        } catch (Exception e) {
            logger.error("Error retrieving question paper solution with ID: " + id, e);
            return ResponseHandler.generateErrorResponse("Failed to retrieve solution", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update question paper solution by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateQuestionPaperSolution(@PathVariable Long id, @RequestBody QuestionPaperSolution solutionDetails) {
        try {
            QuestionPaperSolution updatedSolution = questionPaperSolutionService.updateQuestionPaperSolution(id, solutionDetails);
            return updatedSolution == null
                    ? ResponseHandler.generateNotFoundResponse("Solution not found")
                    : ResponseHandler.generateResponse("Solution updated successfully", HttpStatus.OK, updatedSolution);
        } catch (Exception e) {
            logger.error("Error updating question paper solution with ID: " + id, e);
            return ResponseHandler.generateErrorResponse("Failed to update solution", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete question paper solution by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteQuestionPaperSolution(@PathVariable Long id) {
        try {
            boolean deleted = questionPaperSolutionService.deleteQuestionPaperSolutionById(id);
            return deleted
                    ? ResponseHandler.generateResponse("Solution deleted successfully", HttpStatus.NO_CONTENT, null)
                    : ResponseHandler.generateNotFoundResponse("Solution not found");
        } catch (Exception e) {
            logger.error("Error deleting question paper solution with ID: " + id, e);
            return ResponseHandler.generateErrorResponse("Failed to delete solution", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
