package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
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

    // READ BY ID
    public Student getStudentById(Long id) {

        return studentRepository.findById(id)
                .orElse(null);
    }

    // READ BY EMAIL
    public Student getStudentByEmail(String email) {

        return studentRepository.findByEmail(email);
    }

    // UPDATE
    public Student updateStudent(Long id,
                                 Student updatedStudent) {

        Student existingStudent =
                studentRepository.findById(id)
                        .orElse(null);

        if (existingStudent != null) {

            existingStudent.setName(updatedStudent.getName());
            existingStudent.setEmail(updatedStudent.getEmail());
            existingStudent.setCourse(updatedStudent.getCourse());

            return studentRepository.save(existingStudent);
        }

        return null;
    }

    // DELETE
    public void deleteStudent(Long id) {

        studentRepository.deleteById(id);
    }
}
