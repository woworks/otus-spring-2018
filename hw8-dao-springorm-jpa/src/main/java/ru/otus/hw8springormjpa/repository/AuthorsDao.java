package ru.otus.hw8springormjpa.repository;

import ru.otus.hw8springormjpa.domain.Author;
import ru.otus.hw8springormjpa.domain.Book;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AuthorsDao {

    long countByName(String name);

    void insert(Author author);

    Optional<Author> getByName(String name) throws SQLException;

    List<Author> getAll();

    Optional<Author> getAuthorByBook(Book book);

}
