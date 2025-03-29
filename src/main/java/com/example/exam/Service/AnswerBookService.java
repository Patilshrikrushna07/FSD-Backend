package com.example.exam.Service;

import com.example.exam.Entity.AnswerBook;
import com.example.exam.Repository.AnswerBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerBookService {

    @Autowired
    private AnswerBookRepository answerBookRepository;

    // Get all answer books
    public List<AnswerBook> getAllAnswerBooks() {
        return answerBookRepository.findAll();
    }

    // Create a new answer book
    public AnswerBook createAnswerBook(AnswerBook answerBook) {
        return answerBookRepository.save(answerBook);
    }

    // Get answer book by ID
    public Optional<AnswerBook> getAnswerBookById(Long id) {
        return answerBookRepository.findById(id);
    }

    // Update answer book by ID
    public AnswerBook updateAnswerBook(Long id, AnswerBook answerBookDetails) {
        return answerBookRepository.findById(id).map(answerBook -> {
            answerBook.setStudentId(answerBookDetails.getStudentId());
            answerBook.setExamId(answerBookDetails.getExamId());
            answerBook.setAnswers(answerBookDetails.getAnswers());
            answerBook.setScore(answerBookDetails.getScore());
            return answerBookRepository.save(answerBook);
        }).orElse(null);
    }

    // Delete answer book by ID
    public boolean deleteAnswerBookById(Long id) {
        return answerBookRepository.findById(id).map(answerBook -> {
            answerBookRepository.delete(answerBook);
            return true;
        }).orElse(false);
    }
}
