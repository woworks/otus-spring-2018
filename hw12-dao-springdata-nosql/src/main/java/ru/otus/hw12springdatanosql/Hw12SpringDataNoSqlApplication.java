package ru.otus.hw12springdatanosql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.otus.hw12springdatanosql.repository.BookRepository;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = BookRepository.class)
public class Hw12SpringDataNoSqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(Hw12SpringDataNoSqlApplication.class, args);
    }
}
