package com.example.exam.Repository;

import com.example.exam.Entity.HallTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallTicketRepository extends JpaRepository<HallTicket, Long> {
}
