package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
        properties = {
                "app.test.name=SpringTesting",
                "app.test.version=1.0"
        }
)
public class PropertyInjectionTest {

    @Value("${app.test.name}")
    private String appName;

    @Value("${app.test.version}")
    private String version;

    @Test
    void testProperties() {

        System.out.println("App Name: " + appName);
        System.out.println("Version: " + version);
    }
}
