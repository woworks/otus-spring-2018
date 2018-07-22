package ru.otus.hw6daospringjdbc.dao;

import ru.otus.hw6daospringjdbc.domain.Genre;

import java.sql.SQLException;
import java.util.List;

public interface GenresDao {


    Genre getById(long id) throws SQLException;

    Genre getByName(String name) throws SQLException;

    List<Genre> getAll();

    List<Genre> getGenresByBookId(long id);
}
