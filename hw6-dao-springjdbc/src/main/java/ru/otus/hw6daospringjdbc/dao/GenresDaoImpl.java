package ru.otus.hw6daospringjdbc.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.hw6daospringjdbc.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Repository
public class GenresDaoImpl implements GenresDao {

    private final NamedParameterJdbcOperations jdbc;

    public GenresDaoImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Genre getById(long id) throws SQLException {

        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("genreId", id);

        List<Genre> genresList = jdbc.query("select * from genres where id = :genreId", params, new GenreMapper());

        if (genresList.isEmpty()) {
            throw new SQLException("Could not find genre with id " + id);
        } else if (genresList.size() > 1) {
            throw new SQLException("More than one genre for id " + id);
        } else
            return genresList.get(0);
    }

    @Override
    public Genre getByName(String name) throws SQLException {

        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("genreName", name);

        List<Genre> genresList = jdbc.query("select * from genres where name = :genreName", params, new GenreMapper());

        if (genresList.isEmpty()) {
            System.out.println("Could not find genre with name " + name);
            return null;
        } else if (genresList.size() > 1) {
            throw new SQLException("More than one genre for name " + name);
        }

        return genresList.get(0);
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select * from genres", new HashMap<>(), new GenreMapper());
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }

    @Override
    public List<Genre> getGenresByBookId(long id) {

        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("bookId", id);

        return jdbc.query("select * from genres inner join books_genres on genres.id=books_genres.genre_id" +
                " where books_genres.book_id = :bookId", params, new GenreMapper());

    }
}
