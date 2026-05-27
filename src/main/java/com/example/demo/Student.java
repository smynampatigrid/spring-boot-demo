package com.example.demo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    // Many students can belong to one course
    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference
    private Course course;

    // Default Constructor
    public Student() {
    }

    // Parameterized Constructor
    public Student(String name,
                   String email,
                   Course course) {

        this.name = name;
        this.email = email;
        this.course = course;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", course=" +
                (course != null
                        ? course.getName()
                        : "No Course") +
                '}';
    }
}
