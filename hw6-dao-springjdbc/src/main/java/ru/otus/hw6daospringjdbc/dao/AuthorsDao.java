package ru.otus.hw6daospringjdbc.dao;

import ru.otus.hw6daospringjdbc.domain.Author;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AuthorsDao {

    int countByName(String name);

    int insert(String name);

    Author getById(long id) throws SQLException;

    Optional<Author> getByName(String name) throws SQLException;

    List<Author> getAll();

    Optional<Author> getAuthorByBookId(long id);

}
