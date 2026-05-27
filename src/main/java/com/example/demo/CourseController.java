package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // CREATE
    @PostMapping
    public Course createCourse(@RequestBody Course course) {

        return courseService.saveCourse(course);
    }

    // READ ALL
    @GetMapping
    public List<Course> getAllCourses() {

        return courseService.getAllCourses();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id) {

        return courseService.getCourseById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Course updateCourse(
            @PathVariable Long id,
            @RequestBody Course course) {

        return courseService.updateCourse(id, course);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable Long id) {

        courseService.deleteCourse(id);

        return "Course deleted successfully!";
    }
}