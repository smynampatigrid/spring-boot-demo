# Inversion of Control (IoC) and Dependency Injection (DI)

## Theory

### What is ApplicationContext?
ApplicationContext is the core container in Spring that manages beans and their lifecycle. It is responsible for creating, configuring, and injecting dependencies.

---

### Tradeoffs of Dependency Injection

- Constructor Injection:
    - Recommended approach
    - Ensures immutability
    - Easier testing

- Setter Injection:
    - Useful for optional dependencies
    - Allows flexibility

- Field Injection:
    - Not recommended
    - Hard to test
    - Breaks immutability

---

### Why use @Qualifier?

When multiple beans of the same type exist, Spring cannot decide which one to inject. @Qualifier helps specify the exact bean.

---

### How to avoid loading heavy beans?

- Use @Lazy annotation
- Use profiles (@Profile)
- Conditional bean loading

---

### Spring Lifecycle Stages

1. Bean Instantiation
2. Dependency Injection
3. Initialization (@PostConstruct)
4. Usage
5. Destruction (@PreDestroy)

---

## Practical Observations

### Multiple Beans Issue

When multiple beans of the same type are present, Spring throws:

NoUniqueBeanDefinitionException

---

### Fix using @Qualifier

We resolved ambiguity by specifying the bean:

## 5.3.1 Application Context Configuration

### Difference between @Configuration, @Component, @Service

@Configuration:
Used to define configuration classes that declare one or more @Bean methods. These classes are used by Spring to generate and manage beans.

@Component:
Generic stereotype annotation used to mark a class as a Spring-managed bean.

@Service:
Specialized version of @Component used to indicate service layer classes. It improves readability and semantic meaning.

---

### How can we customize component scanning?

We can customize component scanning using:
- @ComponentScan(basePackages = "com.example.demo")
- Include and exclude filters
- Specifying packages explicitly

---

### What value will a property have if it is defined in two different profiles both active?

The property from the last loaded profile takes precedence and overrides the previous one.

---

### Why use Factory Beans instead of regular beans?

Factory beans are used when bean creation logic is complex. They provide more control over instantiation and configuration of objects.

---

### How to override properties defined in .properties file?

Properties can be overridden using:
- Command line arguments
- Environment variables
- Different profile-specific property files
- @TestPropertySource in tests

---

### Does @PreDestroy get called for prototype beans?

No, @PreDestroy is not called for prototype scoped beans because Spring does not manage their full lifecycle.