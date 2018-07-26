package ru.otus.hw6daospringjdbc.dao;

import ru.otus.hw6daospringjdbc.domain.Genre;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface GenresDao {

    int countByName(String name);

    int insert(String name);

    Genre getById(long id) throws SQLException;

    Optional<Genre> getByName(String name) throws SQLException;

    List<Genre> getAll();

    List<Genre> getGenresByBookId(long id);
}
