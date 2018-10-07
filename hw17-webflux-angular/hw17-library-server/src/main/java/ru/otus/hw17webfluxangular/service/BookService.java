package ru.otus.hw17webfluxangular.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw17webfluxangular.domain.Author;
import ru.otus.hw17webfluxangular.domain.Book;
import ru.otus.hw17webfluxangular.domain.Genre;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BookService {

    Flux<Book> findAll();

    Mono<Book> findById(long id);

    List<Book> getBooksByAuthor(Author author);

    List<Book> getBooksByAuthor(String author) throws SQLException;

    List<Book> getBooksByGenre(Genre genre);

    List<Book> getBooksByGenre(String name) throws SQLException;

    Optional<Book> getBookByTitle(String name);

    void insert(Book book, String authorName, List<String> genreNames);

    void delete(Book book);

    Book save(Long id, String title, Long author, List<Long> genres);

    Mono<Book> save(Book book);
}
