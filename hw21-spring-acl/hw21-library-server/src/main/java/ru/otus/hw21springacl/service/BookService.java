package ru.otus.hw21springacl.service;

import ru.otus.hw21springacl.domain.Author;
import ru.otus.hw21springacl.domain.Book;
import ru.otus.hw21springacl.domain.Genre;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(long id);

    long count();

    List<Book> getBooksByAuthor(Author author);

    List<Book> getBooksByAuthor(String author) throws SQLException;

    List<Book> getBooksByGenre(Genre genre);

    List<Book> getBooksByGenre(String name) throws SQLException;

    Optional<Book> getBookByTitle(String name);

    void insert(Book book, String authorName, List<String> genreNames);

    void delete(Book book);

    Book save(Long id, String title, Long author, List<Long> genres);

    Book save(Book book);
}
