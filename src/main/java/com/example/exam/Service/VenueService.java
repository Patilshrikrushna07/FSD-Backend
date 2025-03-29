package com.example.exam.Service;

import com.example.exam.Entity.Venue;
import com.example.exam.Repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VenueService {

    @Autowired
    private VenueRepository venueRepository;

    // Get all venues
    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    // Create a new venue
    public Venue createVenue(Venue venue) {
        return venueRepository.save(venue);
    }

    // Get venue by ID
    public Optional<Venue> getVenueById(Long venueId) {
        return venueRepository.findById(venueId);
    }

    // Update venue by ID
    public Venue updateVenue(Long venueId, Venue venueDetails) {
        return venueRepository.findById(venueId).map(venue -> {
            venue.setVenueName(venueDetails.getVenueName());
            venue.setLocation(venueDetails.getLocation());
            return venueRepository.save(venue);
        }).orElse(null);
    }

    // Delete venue by ID
    public boolean deleteVenueById(Long venueId) {
        return venueRepository.findById(venueId).map(venue -> {
            venueRepository.delete(venue);
            return true;
        }).orElse(false);
    }
}
