package com.example.demo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class ProdDatabaseService implements DatabaseService {

    @Override
    public void connect() {
        System.out.println("Connected to Production DB");
    }
}
