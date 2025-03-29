package com.example.exam.Controller;

import com.example.exam.Entity.Venue;
import com.example.exam.Service.VenueService;
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
@RequestMapping("/api/venues")
public class VenueController {

    private static final Logger logger = LoggerFactory.getLogger(VenueController.class);

    @Autowired
    private VenueService venueService;

    // Get all venues
    @GetMapping("/get")
    public ResponseEntity<Object> getAllVenues() {
        try {
            List<Venue> venues = venueService.getAllVenues();
            return ResponseHandler.generateResponse("Venues retrieved successfully", HttpStatus.OK, venues);
        } catch (Exception e) {
            logger.error("Error retrieving all venues", e);
            return ResponseHandler.generateErrorResponse("Failed to retrieve venues", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create a new venue
    @PostMapping("/create")
    public ResponseEntity<Object> createVenue(@RequestBody Venue venue) {
        try {
            Venue createdVenue = venueService.createVenue(venue);
            return ResponseHandler.generateResponse("Venue created successfully", HttpStatus.CREATED, createdVenue);
        } catch (Exception e) {
            logger.error("Error creating venue", e);
            return ResponseHandler.generateErrorResponse("Failed to create venue", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get venue by ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getVenueById(@PathVariable Long id) {
        try {
            Optional<Venue> venue = venueService.getVenueById(id);
            return venue
                    .map(value -> ResponseHandler.generateResponse("Venue found", HttpStatus.OK, value))
                    .orElseGet(() -> ResponseHandler.generateNotFoundResponse("Venue not found"));
        } catch (Exception e) {
            logger.error("Error retrieving venue with ID: " + id, e);
            return ResponseHandler.generateErrorResponse("Failed to retrieve venue", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update venue by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateVenue(@PathVariable Long id, @RequestBody Venue venueDetails) {
        try {
            Venue updatedVenue = venueService.updateVenue(id, venueDetails);
            return updatedVenue == null
                    ? ResponseHandler.generateNotFoundResponse("Venue not found")
                    : ResponseHandler.generateResponse("Venue updated successfully", HttpStatus.OK, updatedVenue);
        } catch (Exception e) {
            logger.error("Error updating venue with ID: " + id, e);
            return ResponseHandler.generateErrorResponse("Failed to update venue", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete venue by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteVenue(@PathVariable Long id) {
        try {
            boolean deleted = venueService.deleteVenueById(id);
            return deleted
                    ? ResponseHandler.generateResponse("Venue deleted successfully", HttpStatus.NO_CONTENT, null)
                    : ResponseHandler.generateNotFoundResponse("Venue not found");
        } catch (Exception e) {
            logger.error("Error deleting venue with ID: " + id, e);
            return ResponseHandler.generateErrorResponse("Failed to delete venue", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
