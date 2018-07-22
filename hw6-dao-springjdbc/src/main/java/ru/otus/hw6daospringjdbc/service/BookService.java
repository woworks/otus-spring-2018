package ru.otus.hw6daospringjdbc.service;

import ru.otus.hw6daospringjdbc.domain.Author;
import ru.otus.hw6daospringjdbc.domain.Book;
import ru.otus.hw6daospringjdbc.domain.Genre;

import java.sql.SQLException;
import java.util.List;

public interface BookService {

    List<Book> getAll();

    long count();

    Book getById(long id) throws SQLException;

    List<Book> getBooksByAuthor(Author author);
    List<Book> getBooksByAuthor(String author) throws SQLException;

    List<Book> getBooksByGenre(Genre genre);
    List<Book> getBooksByGenre(String name) throws SQLException;

    void insert(Book book);

    void delete(Book book);

    Book update(Book book);
}
