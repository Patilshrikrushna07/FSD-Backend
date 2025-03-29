package com.example.exam.Repository;

import com.example.exam.Entity.AnswerBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerBookRepository extends JpaRepository<AnswerBook, Long> {
}
