package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class CounterService {

    private int count = 0;

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}