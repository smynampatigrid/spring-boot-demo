package com.example.demo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JpaLifecycleTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    // Clean DB after every test
    @AfterEach
    void cleanup() {
        studentRepository.deleteAll();
        courseRepository.deleteAll();
    }

    // repository.save()
    @Test
    void saveParentWithoutIdUsingRepository() {

        Course course = new Course();
        course.setName("Spring Boot");

        Course savedCourse = courseRepository.save(course);

        assertNotNull(savedCourse.getId());

        System.out.println(savedCourse);
    }

    // entityManager.persist()
    @Test
    @Transactional
    void saveParentWithoutIdUsingPersist() {

        Course course = new Course();
        course.setName("Hibernate");

        entityManager.persist(course);

        assertNotNull(course.getId());

        System.out.println(course);
    }

    // entityManager.merge()
    @Test
    @Transactional
    void saveParentWithoutIdUsingMerge() {

        Course course = new Course();
        course.setName("JPA");

        Course mergedCourse = entityManager.merge(course);

        assertNotNull(mergedCourse.getId());

        System.out.println(mergedCourse);
    }
    // repository.save() with initialized ID
    @Test
    void saveParentWithInitializedIdUsingRepository() {

        Course course = new Course();
        course.setName("Repository ID Test");

        // manually setting ID
        try {
            java.lang.reflect.Field field = Course.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(course, 100L);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        Course savedCourse = courseRepository.save(course);

        assertNotNull(savedCourse);

        System.out.println(savedCourse);
    }

    // persist() with initialized ID
    @Test
    @Transactional
    void saveParentWithInitializedIdUsingPersist() {

        Course course = new Course();
        course.setName("Persist ID Test");

        try {
            java.lang.reflect.Field field = Course.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(course, 200L);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        entityManager.persist(course);

        System.out.println(course);
    }

    // merge() with initialized ID
    @Test
    @Transactional
    void saveParentWithInitializedIdUsingMerge() {

        Course course = new Course();
        course.setName("Merge ID Test");

        try {
            java.lang.reflect.Field field = Course.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(course, 300L);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        Course mergedCourse = entityManager.merge(course);

        assertNotNull(mergedCourse);

        System.out.println(mergedCourse);
    }
}