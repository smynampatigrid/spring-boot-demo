package com.example.demo;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DirtyContextTest {

    @Autowired
    private CounterService counterService;

    @Test
    @Order(1)
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void test1() {

        counterService.increment();

        System.out.println("Count after increment: "
                + counterService.getCount());

        assertEquals(1, counterService.getCount());
    }

    @Test
    @Order(2)
    void test2() {

        System.out.println("Count in second test: "
                + counterService.getCount());

        assertEquals(0, counterService.getCount());
    }
}
