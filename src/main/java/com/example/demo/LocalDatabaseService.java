package com.example.demo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("local")
public class LocalDatabaseService implements DatabaseService {

    @Override
    public void connect() {
        System.out.println("Connected to H2 (LOCAL)");
    }
}
