package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;
    private final DatabaseService databaseService;
    private final AppProperties appProperties;
    private final ApplicationContext context;
    private final Environment environment;
    private final TimeProperties timeProperties; // 👈 NEW

    public Bootstrap(PublisherRepository publisherRepository,
                     AuthorRepository authorRepository,
                     DatabaseService databaseService,
                     AppProperties appProperties,
                     ApplicationContext context,
                     Environment environment,
                     TimeProperties timeProperties) {

        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
        this.databaseService = databaseService;
        this.appProperties = appProperties;
        this.context = context;
        this.environment = environment;
        this.timeProperties = timeProperties; // 👈 NEW
    }

    @Override
    public void run(String... args) throws Exception {

        databaseService.connect();

        System.out.println("Config Name: " + appProperties.getName());
        System.out.println("Config Version: " + appProperties.getVersion());
        System.out.println("Config Author: " + appProperties.getAuthor());

        // 👇 PRINT DATE/TIME (NEW)
        System.out.println("Date: " + timeProperties.getDate());
        System.out.println("Time: " + timeProperties.getTime());
        System.out.println("DateTime: " + timeProperties.getDatetime());

        // 👇 BEANS
        System.out.println("----- BEANS -----");
        String[] beanNames = context.getBeanDefinitionNames();
        for (String bean : beanNames) {
            System.out.println(bean);
        }

        // 👇 ENVIRONMENT
        System.out.println("----- ENVIRONMENT -----");
        System.out.println("Active Profile: " + environment.getActiveProfiles()[0]);
        System.out.println("App Name: " + environment.getProperty("app.name"));

        // Publisher
        Publisher publisher = new Publisher();
        publisher.setName("My Publisher");
        publisher.setAddressLine1("Street 1");
        publisher.setCity("Hyderabad");
        publisher.setState("Telangana");
        publisher.setZip("500001");
        publisherRepository.save(publisher);

        // Author
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");
        authorRepository.save(author);

        System.out.println("Publisher saved: " + publisher);
        System.out.println("Author saved: " + author);
    }
}
