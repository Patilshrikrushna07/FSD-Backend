package com.example.exam.Controller;

import com.example.exam.Entity.HallTicket;
import com.example.exam.Service.HallTicketService;
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
@RequestMapping("/api/halltickets")
public class HallTicketController {

    private static final Logger logger = LoggerFactory.getLogger(HallTicketController.class);

    @Autowired
    private HallTicketService hallTicketService;

    // Get all hall tickets
    @GetMapping("/get")
    public ResponseEntity<Object> getAllHallTickets() {
        try {
            List<HallTicket> hallTickets = hallTicketService.getAllHallTickets();
            return ResponseHandler.generateResponse("Hall tickets retrieved successfully", HttpStatus.OK, hallTickets);
        } catch (Exception e) {
            logger.error("Error retrieving all hall tickets", e);
            return ResponseHandler.generateErrorResponse("Failed to retrieve hall tickets", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create a new hall ticket
    @PostMapping("/create")
    public ResponseEntity<Object> createHallTicket(@RequestBody HallTicket hallTicket) {
        try {
            HallTicket createdHallTicket = hallTicketService.createHallTicket(hallTicket);
            return ResponseHandler.generateResponse("Hall ticket created successfully", HttpStatus.CREATED, createdHallTicket);
        } catch (Exception e) {
            logger.error("Error creating hall ticket", e);
            return ResponseHandler.generateErrorResponse("Failed to create hall ticket", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get hall ticket by ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getHallTicketById(@PathVariable Long id) {
        try {
            Optional<HallTicket> hallTicket = hallTicketService.getHallTicketById(id);
            return hallTicket
                    .map(value -> ResponseHandler.generateResponse("Hall ticket found", HttpStatus.OK, value))
                    .orElseGet(() -> ResponseHandler.generateNotFoundResponse("Hall ticket not found"));
        } catch (Exception e) {
            logger.error("Error retrieving hall ticket with ID: " + id, e);
            return ResponseHandler.generateErrorResponse("Failed to retrieve hall ticket", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update hall ticket by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateHallTicket(@PathVariable Long id, @RequestBody HallTicket hallTicketDetails) {
        try {
            HallTicket updatedHallTicket = hallTicketService.updateHallTicket(id, hallTicketDetails);
            return updatedHallTicket == null
                    ? ResponseHandler.generateNotFoundResponse("Hall ticket not found")
                    : ResponseHandler.generateResponse("Hall ticket updated successfully", HttpStatus.OK, updatedHallTicket);
        } catch (Exception e) {
            logger.error("Error updating hall ticket with ID: " + id, e);
            return ResponseHandler.generateErrorResponse("Failed to update hall ticket", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete hall ticket by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteHallTicket(@PathVariable Long id) {
        try {
            boolean deleted = hallTicketService.deleteHallTicketById(id);
            return deleted
                    ? ResponseHandler.generateResponse("Hall ticket deleted successfully", HttpStatus.NO_CONTENT, null)
                    : ResponseHandler.generateNotFoundResponse("Hall ticket not found");
        } catch (Exception e) {
            logger.error("Error deleting hall ticket with ID: " + id, e);
            return ResponseHandler.generateErrorResponse("Failed to delete hall ticket", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
