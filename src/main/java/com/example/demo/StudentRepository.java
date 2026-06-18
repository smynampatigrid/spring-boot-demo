package com.example.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository
        extends JpaRepository<Student, Long> {
    Student findByEmail(String email);

    Page<Student> findByNameContainingIgnoreCase(
            String name,
            Pageable pageable);

}

