package com.example.exam.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "answer_books")
public class AnswerBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id")
    private Long studentId; // Assuming the Student ID is a Long

    @Column(name = "exam_id")
    private Long examId; // Assuming the Exam ID is a Long

    @ElementCollection // Assuming answers are stored as a list of strings
    private List<String> answers;

    private Integer score;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
