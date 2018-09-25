package ru.otus.hw12springdatanosql.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw12springdatanosql.domain.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    Optional<Book> findByTitle(String title);

    List<Book> findByGenres(final String genre);

    List<Book> findByAuthor(final String author);


}
