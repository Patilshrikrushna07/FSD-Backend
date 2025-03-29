package com.example.exam.Service;

import com.example.exam.Entity.Teacher;
import com.example.exam.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    // Get all teachers
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    // Create a new teacher
    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    // Get teacher by ID
    public Optional<Teacher> getTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId);
    }

    // Update teacher by ID
    public Teacher updateTeacher(Long teacherId, Teacher teacherDetails) {
        return teacherRepository.findById(teacherId).map(teacher -> {
            teacher.setName(teacherDetails.getName());
            teacher.setSubject(teacherDetails.getSubject());
            teacher.setEmail(teacherDetails.getEmail());
            teacher.setExperience(teacherDetails.getExperience());
            return teacherRepository.save(teacher);
        }).orElse(null);
    }

    // Delete teacher by ID
    public boolean deleteTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId).map(teacher -> {
            teacherRepository.delete(teacher);
            return true;
        }).orElse(false);
    }
}
