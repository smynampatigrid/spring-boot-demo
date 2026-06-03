# 5.4 Introduction to Spring Test {PracticaL}

## Overview

This module focuses on Spring Test, an important component of the Spring Framework that enables comprehensive testing of Spring applications. The implementation includes integration testing, application context testing, dirty context handling, property injection, and endpoint testing using Spring Boot Test Framework.

---

# Questions and Answers

## 1. How can we configure the application context when running tests with `@SpringBootTest` annotation?

The application context can be configured using the `@SpringBootTest` annotation by specifying configuration classes, properties, or web environment settings.

### Example

```java
@SpringBootTest(
    classes = DemoApplication.class,
    properties = {
        "server.port=8081"
    },
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
```

### Purpose

* Load specific configuration classes
* Override application properties during tests
* Configure the web environment
* Customize the Spring application context

---

## 2. How can you exclude auto configuration from a test?

Auto configuration can be excluded using `@EnableAutoConfiguration`.

### Example

```java
@SpringBootTest
@EnableAutoConfiguration(exclude = {
    DataSourceAutoConfiguration.class
})
```

### Purpose

* Avoid unnecessary bean loading
* Skip database configuration when not needed
* Improve testing performance
* Isolate components during testing

---

## 3. How many application contexts can be cached when running tests?

By default, Spring Test caches up to **32 application contexts**.

### Configuration

```properties
spring.test.context.cache.maxSize=32
```

### Side Effects of Increasing Cache Size

* Faster repeated test execution
* More memory consumption

### Side Effects if There Was No Caching

* Application context recreated for every test
* Very slow test execution
* Reduced performance in large applications

---

## 4. Can `@MockBean` be used if the bean is not already defined in the application context?

Yes. `@MockBean` can:

* Replace an existing bean
* Create a new mock bean if none exists

### Example

```java
@MockBean
private UserService userService;
```

### Purpose

* Mock dependencies during testing
* Isolate application components
* Simplify unit and integration testing

---

# Exercises

## 1. Dirty Context Demonstration

### CounterService.java

```java
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
```

---

### DirtyContextTest.java

```java
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

        assertEquals(1, counterService.getCount());
    }

    @Test
    @Order(2)
    void test2() {

        assertEquals(0, counterService.getCount());
    }
}
```

### Explanation

This test demonstrates how shared application context can affect test correctness. Without `@DirtiesContext`, the modified bean state persists between tests, causing failures.

---

# 2. Property Injection Without `.properties` File

## PropertyInjectionTest.java

```java
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
```

### Explanation

Properties are directly injected into the test using the `properties` attribute of `@SpringBootTest` without using an external `.properties` file.

---

# 3. Integration Testing

## AuthorControllerIntegrationTest.java

```java
package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAuthorEndpoint() throws Exception {

        mockMvc.perform(get("/authors"))
                .andExpect(status().isOk());
    }
}
```

### Explanation

This integration test validates the functionality of the application endpoint using:

* Spring Boot application context
* MockMvc
* Controller endpoint testing

---

# Conclusion

Through this module, Spring Boot testing concepts such as:

* Integration testing
* Context caching
* Dirty context handling
* Property injection
* Mocking dependencies
* Endpoint testing

were successfully implemented and tested using the Spring Test Framework.
