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

    // ------------------------------------------------
    // Parent WITHOUT ID
    // ------------------------------------------------

    @Test
    void saveParentWithoutIdUsingRepository() {

        Course course = new Course();
        course.setName("Spring Boot");

        Course savedCourse = courseRepository.save(course);

        assertNotNull(savedCourse.getId());

        System.out.println(savedCourse);
    }

    @Test
    @Transactional
    void saveParentWithoutIdUsingPersist() {

        Course course = new Course();
        course.setName("Hibernate");

        entityManager.persist(course);

        assertNotNull(course.getId());

        System.out.println(course);
    }

    @Test
    @Transactional
    void saveParentWithoutIdUsingMerge() {

        Course course = new Course();
        course.setName("JPA");

        Course mergedCourse = entityManager.merge(course);

        assertNotNull(mergedCourse.getId());

        System.out.println(mergedCourse);
    }

    // ------------------------------------------------
    // Parent WITH initialized ID
    // ------------------------------------------------

    @Test
    void saveParentWithInitializedIdUsingRepository() {

        Course course = new Course();
        course.setName("Repository ID Test");

        try {
            java.lang.reflect.Field field =
                    Course.class.getDeclaredField("id");

            field.setAccessible(true);
            field.set(course, 100L);

        } catch (Exception e) {
            fail(e.getMessage());
        }

        Course savedCourse = courseRepository.save(course);

        assertNotNull(savedCourse);

        System.out.println(savedCourse);
    }

    @Test
    @Transactional
    void saveParentWithInitializedIdUsingPersist() {

        Course course = new Course();
        course.setName("Persist ID Test");

        try {
            java.lang.reflect.Field field =
                    Course.class.getDeclaredField("id");

            field.setAccessible(true);
            field.set(course, 200L);

        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertThrows(Exception.class, () -> {
            entityManager.persist(course);
            entityManager.flush();
        });
    }

    @Test
    @Transactional
    void saveParentWithInitializedIdUsingMerge() {

        Course course = new Course();
        course.setName("Merge ID Test");

        try {
            java.lang.reflect.Field field =
                    Course.class.getDeclaredField("id");

            field.setAccessible(true);
            field.set(course, 300L);

        } catch (Exception e) {
            fail(e.getMessage());
        }

        Course mergedCourse = entityManager.merge(course);

        assertNotNull(mergedCourse);

        System.out.println(mergedCourse);
    }

    // ------------------------------------------------
    // Duplicate Parent ID
    // ------------------------------------------------

    @Test
    @Transactional
    void saveDuplicateParentIdUsingMerge() {

        Course original = new Course();
        original.setName("Original Course");

        entityManager.persist(original);
        entityManager.flush();

        Course duplicate = new Course();
        duplicate.setName("Duplicate Course");

        try {
            java.lang.reflect.Field field =
                    Course.class.getDeclaredField("id");

            field.setAccessible(true);
            field.set(duplicate, original.getId());

        } catch (Exception e) {
            fail(e.getMessage());
        }

        Course merged = entityManager.merge(duplicate);

        assertNotNull(merged);

        System.out.println(merged);
    }

    // ------------------------------------------------
    // Parent with NEW Children
    // ------------------------------------------------

    @Test
    void saveParentWithNewChildrenUsingRepository() {

        Course course = new Course();
        course.setName("Spring");

        Student s1 =
                new Student("Sreeja", "sreeja@test.com", course);

        Student s2 =
                new Student("Alex", "alex@test.com", course);

        course.addStudent(s1);
        course.addStudent(s2);

        Course saved = courseRepository.save(course);

        assertEquals(2, saved.getStudents().size());

        System.out.println(saved);
    }

    // ------------------------------------------------
    // Child WITHOUT Parent
    // ------------------------------------------------

    @Test
    void saveChildWithoutParentUsingRepository() {

        Student student =
                new Student("John", "john@test.com", null);

        Student saved = studentRepository.save(student);

        assertNotNull(saved.getId());

        System.out.println(saved);
    }

    // ------------------------------------------------
    // Child WITH transient Parent
    // ------------------------------------------------

    @Test
    void saveChildWithTransientParent() {

        Course course = new Course();
        course.setName("Transient Parent");

        // Save parent first
        Course savedCourse = courseRepository.save(course);

        Student student = new Student();
        student.setName("Sreeja");
        student.setEmail("sreeja@test.com");
        student.setCourse(savedCourse);

        Student savedStudent = studentRepository.save(student);

        assertNotNull(savedStudent.getId());

        System.out.println(savedStudent);
    }

    // ------------------------------------------------
    // Dirty Checking
    // ------------------------------------------------

    @Test
    @Transactional
    void dirtyCheckingTest() {

        Course course = new Course();
        course.setName("Old Name");

        entityManager.persist(course);
        entityManager.flush();

        Course fetched =
                courseRepository.findById(course.getId())
                        .orElseThrow();

        fetched.setName("Updated Name");

        entityManager.flush();

        Course updated =
                courseRepository.findById(course.getId())
                        .orElseThrow();

        assertEquals("Updated Name", updated.getName());

        System.out.println(updated);
    }
}
