package com.example.exam.Service;

import com.example.exam.Entity.HallTicket;
import com.example.exam.Repository.HallTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HallTicketService {

    @Autowired
    private HallTicketRepository hallTicketRepository;

    // Get all hall tickets
    public List<HallTicket> getAllHallTickets() {
        return hallTicketRepository.findAll();
    }

    // Create a new hall ticket
    public HallTicket createHallTicket(HallTicket hallTicket) {
        return hallTicketRepository.save(hallTicket);
    }

    // Get hall ticket by ID
    public Optional<HallTicket> getHallTicketById(Long hallTicketId) {
        return hallTicketRepository.findById(hallTicketId);
    }

    // Update hall ticket by ID
    public HallTicket updateHallTicket(Long hallTicketId, HallTicket hallTicketDetails) {
        return hallTicketRepository.findById(hallTicketId).map(hallTicket -> {
            hallTicket.setTicketId(hallTicketDetails.getTicketId());
            hallTicket.setStudentId(hallTicketDetails.getStudentId());
            hallTicket.setTicketNumber(hallTicketDetails.getTicketNumber());
            return hallTicketRepository.save(hallTicket);
        }).orElse(null);
    }

    // Delete hall ticket by ID
    public boolean deleteHallTicketById(Long hallTicketId) {
        return hallTicketRepository.findById(hallTicketId).map(hallTicket -> {
            hallTicketRepository.delete(hallTicket);
            return true;
        }).orElse(false);
    }
}
