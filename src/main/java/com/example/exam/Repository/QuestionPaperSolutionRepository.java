package com.example.exam.Repository;

import com.example.exam.Entity.QuestionPaperSolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionPaperSolutionRepository extends JpaRepository<QuestionPaperSolution, Long> {
}
