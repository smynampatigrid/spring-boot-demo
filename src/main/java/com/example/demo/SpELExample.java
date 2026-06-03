package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class SpELExample {

    @Value("#{'${app.topics}'.split('-')}")
    private String[] topics;

    @PostConstruct
    public void printTopics() {
        System.out.println("Topics: " + Arrays.toString(topics));
    }
}
