# Spring Boot - Configuration & Autoconfiguration Assignment

## 📌 Overview
This project demonstrates core Spring Boot concepts including:
- Inversion of Control (IoC)
- Dependency Injection (DI)
- Application Context Configuration
- Profiles and External Configuration
- SpEL (Spring Expression Language)
- @ConfigurationProperties
- Spring Boot Autoconfiguration

---

## ⚙️ Implementations

### 1. Dependency Injection
- Constructor Injection
- Setter Injection
- Primary Bean usage
- Fixed Bad Spring Context using constructor injection

---

### 2. Application Configuration
- Used `application.properties`
- Externalized configuration
- Organized properties using prefixes

---

### 3. Profiles
Implemented environment-based configurations:
- `local` → H2 Database
- `dev` → PostgreSQL
- `prod` → Environment variables

---

### 4. @Value Injection
Used for simple property values:

app.name  
app.version

---

### 5. @ConfigurationProperties
Used for structured configuration:

app.config.*  
app.time.*

Mapped to:
- AppProperties
- TimeProperties

---

### 6. SpEL (Spring Expression Language)
Converted string to array:

app.topics=java-spring-boot

Output:

[java, spring, boot]

---

### 7. ApplicationContext & Environment
Using `CommandLineRunner`:
- Printed all beans in the application context
- Printed active profile
- Accessed environment properties

---

### 8. Autoconfiguration
Enabled debugging using:

debug=true

Observed:
- Positive matches (applied configurations)
- Negative matches (skipped configurations)
- Condition-based decision making

---

## 🧠 Key Learnings

- Spring Boot automatically configures beans based on:
  - Classpath dependencies
  - Existing beans
  - Application properties

- Example:
  - DataSource auto-configured because H2 dependency exists
  - Tomcat auto-started due to web starter dependency

---

## ❓ Questions

### 1. Difference between @Configuration, @Component, @Service
- `@Configuration` → defines bean configuration
- `@Component` → generic Spring bean
- `@Service` → business logic layer (semantic)

---

### 2. Component Scanning
Customized using:
- `@ComponentScan`
- Base packages
- Include/exclude filters

---

### 3. Property precedence
If multiple profiles are active:
- The last loaded profile overrides previous ones

---

### 4. Factory Beans
Used when:
- Object creation is complex
- Custom instantiation logic is required

---

### 5. Overriding properties
Can be done using:
- Profiles
- Environment variables
- Command-line arguments

---

### 6. Prototype bean lifecycle
- `@PreDestroy` is NOT called for prototype beans

---

### 7. Regular Configuration vs Autoconfiguration
- Regular → manually defined beans
- Autoconfiguration → automatic based on conditions

---

### 8. Conditional Annotations
- Work in both regular and autoconfig classes
- Mainly used in autoconfiguration

---

### 9. Customizing Autoconfiguration
- Override beans
- Use properties
- Exclude configurations:

```java
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
