package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // CREATE
    public Course saveCourse(Course course) {

        return courseRepository.save(course);
    }

    // READ ALL
    public List<Course> getAllCourses() {

        return courseRepository.findAll();
    }

    // READ BY ID
    public Course getCourseById(Long id) {

        return courseRepository.findById(id)
                .orElse(null);
    }

    // UPDATE
    public Course updateCourse(Long id,
                               Course updatedCourse) {

        Course existingCourse =
                courseRepository.findById(id)
                        .orElse(null);

        if (existingCourse != null) {

            existingCourse.setName(updatedCourse.getName());

            return courseRepository.save(existingCourse);
        }

        return null;
    }

    // DELETE
    public void deleteCourse(Long id) {

        courseRepository.deleteById(id);
    }
}
