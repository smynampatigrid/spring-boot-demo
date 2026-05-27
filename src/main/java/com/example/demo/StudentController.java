package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // CREATE
    @PostMapping
    public Student createStudent(@RequestBody Student student) {

        return studentService.saveStudent(student);
    }

    // READ ALL
    @GetMapping
    public List<Student> getAllStudents() {

        return studentService.getAllStudents();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {

        return studentService.getStudentById(id);
    }

    // READ BY EMAIL
    @GetMapping("/email/{email}")
    public Student getStudentByEmail(
            @PathVariable String email) {

        return studentService.getStudentByEmail(email);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable Long id,
            @RequestBody Student student) {

        return studentService.updateStudent(id, student);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return "Student deleted successfully!";
    }
}
