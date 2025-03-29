package com.example.exam.Service;

import com.example.exam.Entity.Course;
import com.example.exam.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // Get all courses
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Create a new course
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    // Get course by ID
    public Optional<Course> getCourseById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    // Update course by ID
    public Course updateCourse(Long courseId, Course courseDetails) {
        return courseRepository.findById(courseId).map(course -> {
            course.setCourseName(courseDetails.getCourseName());
            course.setCourseDescription(courseDetails.getCourseDescription());
            return courseRepository.save(course);
        }).orElse(null);
    }

    // Delete course by ID
    public boolean deleteCourseById(Long courseId) {
        return courseRepository.findById(courseId).map(course -> {
            courseRepository.delete(course);
            return true;
        }).orElse(false);
    }
}
