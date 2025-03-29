package com.example.exam.Controller;

import com.example.exam.Entity.AnswerBook;
import com.example.exam.Service.AnswerBookService;
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
@RequestMapping("/api/answerbooks")
public class AnswerBookController {

    private static final Logger logger = LoggerFactory.getLogger(AnswerBookController.class);

    @Autowired
    private AnswerBookService answerBookService;

    // Get all answer books
    @GetMapping("/get")
    public ResponseEntity<Object> getAllAnswerBooks() {
        try {
            List<AnswerBook> answerBooks = answerBookService.getAllAnswerBooks();
            return ResponseHandler.generateResponse("Answer books retrieved successfully", HttpStatus.OK, answerBooks);
        } catch (Exception e) {
            logger.error("Error retrieving all answer books", e);
            return ResponseHandler.generateErrorResponse("Failed to retrieve answer books", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create a new answer book
    @PostMapping("/create")
    public ResponseEntity<Object> createAnswerBook(@RequestBody AnswerBook answerBook) {
        try {
            AnswerBook createdAnswerBook = answerBookService.createAnswerBook(answerBook);
            return ResponseHandler.generateResponse("Answer book created successfully", HttpStatus.CREATED, createdAnswerBook);
        } catch (Exception e) {
            logger.error("Error creating answer book", e);
            return ResponseHandler.generateErrorResponse("Failed to create answer book", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get answer book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getAnswerBookById(@PathVariable Long id) {
        try {
            Optional<AnswerBook> answerBook = answerBookService.getAnswerBookById(id);
            if (answerBook.isPresent()) {
                return ResponseHandler.generateResponse("Answer book found", HttpStatus.OK, answerBook.get());
            } else {
                return ResponseHandler.generateNotFoundResponse("Answer book not found");
            }
        } catch (Exception e) {
            logger.error("Error retrieving answer book with ID: " + id, e);
            return ResponseHandler.generateErrorResponse("Failed to retrieve answer book", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update answer book by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateAnswerBook(@PathVariable Long id, @RequestBody AnswerBook answerBookDetails) {
        try {
            AnswerBook updatedAnswerBook = answerBookService.updateAnswerBook(id, answerBookDetails);
            if (updatedAnswerBook != null) {
                return ResponseHandler.generateResponse("Answer book updated successfully", HttpStatus.OK, updatedAnswerBook);
            } else {
                return ResponseHandler.generateNotFoundResponse("Answer book not found");
            }
        } catch (Exception e) {
            logger.error("Error updating answer book with ID: " + id, e);
            return ResponseHandler.generateErrorResponse("Failed to update answer book", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete answer book by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteAnswerBook(@PathVariable Long id) {
        try {
            boolean deleted = answerBookService.deleteAnswerBookById(id);
            if (deleted) {
                return ResponseHandler.generateResponse("Answer book deleted successfully", HttpStatus.NO_CONTENT, null);
            } else {
                return ResponseHandler.generateNotFoundResponse("Answer book not found");
            }
        } catch (Exception e) {
            logger.error("Error deleting answer book with ID: " + id, e);
            return ResponseHandler.generateErrorResponse("Failed to delete answer book", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
