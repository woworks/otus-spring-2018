package ru.otus.hw12springdatanosql.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import ru.otus.hw12springdatanosql.domain.Book;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findByTitle(String title);

    @Query("{'genres': ?0}")
    List<Book> findByGenre(final String genre);


    @Query("{'author': ?0}")
    List<Book> findByAuthor(final String author);


}
