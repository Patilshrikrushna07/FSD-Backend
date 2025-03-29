package com.example.exam.Service;

import com.example.exam.Entity.QuestionPaperSolution;
import com.example.exam.Repository.QuestionPaperSolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionPaperSolutionService {

    @Autowired
    private QuestionPaperSolutionRepository questionPaperSolutionRepository;

    // Get all question paper solutions
    public List<QuestionPaperSolution> getAllQuestionPaperSolutions() {
        return questionPaperSolutionRepository.findAll();
    }

    // Create a new question paper solution
    public QuestionPaperSolution createQuestionPaperSolution(QuestionPaperSolution solution) {
        return questionPaperSolutionRepository.save(solution);
    }

    // Get question paper solution by ID
    public Optional<QuestionPaperSolution> getQuestionPaperSolutionById(Long solutionId) {
        return questionPaperSolutionRepository.findById(solutionId);
    }

    // Update question paper solution by ID
    public QuestionPaperSolution updateQuestionPaperSolution(Long solutionId, QuestionPaperSolution solutionDetails) {
        return questionPaperSolutionRepository.findById(solutionId).map(solution -> {
            solution.setQuestionPaperId(solutionDetails.getQuestionPaperId());
            solution.setSolutionText(solutionDetails.getSolutionText());
            solution.setAnswerType(solutionDetails.getAnswerType());
            solution.setMarksObtained(solutionDetails.getMarksObtained());
            return questionPaperSolutionRepository.save(solution);
        }).orElse(null);
    }

    // Delete question paper solution by ID
    public boolean deleteQuestionPaperSolutionById(Long solutionId) {
        return questionPaperSolutionRepository.findById(solutionId).map(solution -> {
            questionPaperSolutionRepository.delete(solution);
            return true;
        }).orElse(false);
    }
}
