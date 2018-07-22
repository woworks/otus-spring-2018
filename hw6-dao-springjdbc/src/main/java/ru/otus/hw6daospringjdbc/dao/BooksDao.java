package ru.otus.hw6daospringjdbc.dao;

import ru.otus.hw6daospringjdbc.domain.Author;
import ru.otus.hw6daospringjdbc.domain.Book;
import ru.otus.hw6daospringjdbc.domain.Genre;

import java.sql.SQLException;
import java.util.List;

public interface BooksDao {

    int count();

    void insert(Book book);

    Book getById(long id) throws SQLException;

    List<Book> getBooksByGenre(Genre genre);

    List<Book> getBooksByAuthor(Author author);

    List<Book> getAll();
}
