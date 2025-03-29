package com.example.exam.Service;

import com.example.exam.Entity.Student;
import com.example.exam.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Create a new student
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // Get student by ID
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    // Update student by ID
    public Student updateStudent(Long id, Student studentDetails) {
        return studentRepository.findById(id).map(student -> {
            student.setName(studentDetails.getName());
            student.setEmail(studentDetails.getEmail());
            student.setCourse(studentDetails.getCourse());
            return studentRepository.save(student);
        }).orElse(null);
    }

    // Partially update student by ID
    public Student partiallyUpdateStudent(Long id, Student studentDetails) {
        return studentRepository.findById(id).map(student -> {
            if (studentDetails.getName() != null) student.setName(studentDetails.getName());
            if (studentDetails.getEmail() != null) student.setEmail(studentDetails.getEmail());
            if (studentDetails.getCourse() != null) student.setCourse(studentDetails.getCourse());
            return studentRepository.save(student);
        }).orElse(null);
    }

    // Delete student by ID
    public boolean deleteStudentById(Long id) {
        return studentRepository.findById(id).map(student -> {
            studentRepository.delete(student);
            return true;
        }).orElse(false);
    }
}
