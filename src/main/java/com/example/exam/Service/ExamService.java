package com.example.exam.Service;

import com.example.exam.Entity.Exam;
import com.example.exam.Repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    // Get all exams
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    // Create a new exam
    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    // Get exam by ID
    public Optional<Exam> getExamById(Long examId) {
        return examRepository.findById(examId);
    }

    // Update exam by ID
    public Exam updateExam(Long examId, Exam examDetails) {
        return examRepository.findById(examId).map(exam -> {
            exam.setCourseId(examDetails.getCourseId());
            exam.setVenueId(examDetails.getVenueId());
            exam.setDate(examDetails.getDate());
            exam.setTime(examDetails.getTime());
            exam.setTotalMarks(examDetails.getTotalMarks());
            exam.setAcademicYear(examDetails.getAcademicYear());
            exam.setExamType(examDetails.getExamType());
            return examRepository.save(exam);
        }).orElse(null);
    }

    // Delete exam by ID
    public boolean deleteExamById(Long examId) {
        return examRepository.findById(examId).map(exam -> {
            examRepository.delete(exam);
            return true;
        }).orElse(false);
    }
}
