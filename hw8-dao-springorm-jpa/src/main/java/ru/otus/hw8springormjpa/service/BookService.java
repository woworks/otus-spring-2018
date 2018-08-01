package ru.otus.hw8springormjpa.service;

import ru.otus.hw8springormjpa.domain.Author;
import ru.otus.hw8springormjpa.domain.Book;
import ru.otus.hw8springormjpa.domain.Genre;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getAll();

    long count();

    List<Book> getBooksByAuthor(Author author);
    List<Book> getBooksByAuthor(String author) throws SQLException;

    List<Book> getBooksByGenre(Genre genre);

    List<Book> getBooksByGenre(String name) throws SQLException;

    Optional<Book> getBookByName(String name);

    void insert(Book book, String authorName, List<String> genreNames);

    void delete(Book book);

    Book update(Book book);
}
