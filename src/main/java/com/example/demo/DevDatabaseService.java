package com.example.demo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class DevDatabaseService implements DatabaseService {

    @Override
    public void connect() {
        System.out.println("Connected to PostgreSQL (DEV)");
    }
}
