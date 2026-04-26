package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;

    public Bootstrap(PublisherRepository publisherRepository,
                     AuthorRepository authorRepository) {
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Publisher
        Publisher publisher = new Publisher();
        publisher.setName("My Publisher");
        publisher.setAddressLine1("Street 1");
        publisher.setCity("Hyderabad");
        publisher.setState("Telangana");
        publisher.setZip("500001");
        publisherRepository.save(publisher);

        // Author (THIS WAS MISSING)
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");
        authorRepository.save(author);

        System.out.println("Publisher saved: " + publisher);
        System.out.println("Author saved: " + author);
    }
}
