package ru.otus.hw6daospringjdbc.dao;

import ru.otus.hw6daospringjdbc.domain.Author;

import java.sql.SQLException;
import java.util.List;

public interface AuthorsDao {

    int countByName(String name);

    int insert(String name);

    Author getById(long id) throws SQLException;

    Author getByName(String name) throws SQLException;

    List<Author> getAll();

    Author getAuthorByBookId(long id);

}
