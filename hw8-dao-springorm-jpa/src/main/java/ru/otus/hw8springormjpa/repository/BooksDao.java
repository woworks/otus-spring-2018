package ru.otus.hw8springormjpa.repository;

import ru.otus.hw8springormjpa.domain.Author;
import ru.otus.hw8springormjpa.domain.Book;
import ru.otus.hw8springormjpa.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface BooksDao {

    long count();

    void insert(Book book);

    List<Book> getBooksByGenre(Genre genre);

    Optional<Book> getBookByName(String name);

    List<Book> getBooksByAuthor(Author author);

    List<Book> getAll();
}
