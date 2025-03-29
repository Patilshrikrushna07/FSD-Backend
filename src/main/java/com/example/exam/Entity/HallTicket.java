package com.example.exam.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "hall_tickets")
public class HallTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hallTicketId;

    private Long ticketId;
    private Long studentId;
    private String ticketNumber;

    // Getters and Setters

    public Long getHallTicketId() {
        return hallTicketId;
    }

    public void setHallTicketId(Long hallTicketId) {
        this.hallTicketId = hallTicketId;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }
}
