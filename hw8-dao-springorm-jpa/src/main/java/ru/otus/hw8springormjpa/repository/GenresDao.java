package ru.otus.hw8springormjpa.repository;

import ru.otus.hw8springormjpa.domain.Book;
import ru.otus.hw8springormjpa.domain.Genre;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface GenresDao {

    void insert(Genre genre);

    Optional<Genre> getByName(String name) throws SQLException;

    List<Genre> getAll();

    List<Genre> getGenresByBook(Book book);
}
