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

```java
public Bar(@Qualifier("fooImpl") Foo foo)