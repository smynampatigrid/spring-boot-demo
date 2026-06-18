package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    // CREATE
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // READ ALL
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // PAGINATION + SEARCH
    public Page<Student> searchStudents(
            String name,
            int page,
            int size) {

        return studentRepository
                .findByNameContainingIgnoreCase(
                        name,
                        PageRequest.of(page, size));
    }

    // READ BY ID
    public Student getStudentById(Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id: " + id));
    }

    // READ BY EMAIL
    public Student getStudentByEmail(String email) {

        return studentRepository.findByEmail(email);
    }

    // UPDATE
    public Student updateStudent(
            Long id,
            Student updatedStudent) {

        Student existingStudent =
                studentRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Student not found with id: " + id));

        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setCourse(updatedStudent.getCourse());

        return studentRepository.save(existingStudent);
    }

    // DELETE
    public void deleteStudent(Long id) {

        Student student =
                studentRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Student not found with id: " + id));

        studentRepository.delete(student);
    }

}
