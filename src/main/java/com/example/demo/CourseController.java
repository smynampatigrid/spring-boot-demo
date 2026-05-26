package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public Course createCourse(@RequestBody Course course) {

        return courseService.saveCourse(course);
    }

    @GetMapping
    public List<Course> getAllCourses() {

        return courseService.getAllCourses();
    }
}
