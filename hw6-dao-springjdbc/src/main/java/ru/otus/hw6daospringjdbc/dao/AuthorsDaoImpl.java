package ru.otus.hw6daospringjdbc.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.hw6daospringjdbc.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorsDaoImpl implements AuthorsDao {

    private final NamedParameterJdbcOperations jdbc;

    public AuthorsDaoImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int countByName(String name) {
        final Map<String, Object> params = Collections.singletonMap("authorName", name);
        return jdbc.queryForObject("select count(*) from authors where name=:authorName", params, Integer.class);
    }

    @Override
    public int insert(String name) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource sqlParms =
                new MapSqlParameterSource().addValue("authorName", name);
        jdbc.update("insert into authors (name) values (:authorName)", sqlParms,  keyHolder, new String[]{"id"});

        return keyHolder.getKey().intValue();
    }

    @Override
    public Author getById(long id) throws SQLException {

        final Map<String, Object> params = Collections.singletonMap("authorId", id);

        List<Author> authorsList = jdbc.query("select * from authors where id = :authorId", params, new AuthorMapper());

        if (authorsList.isEmpty()) {
            throw new SQLException("Could not find Author with id " + id);
        } else if (authorsList.size() > 1) {
            throw new SQLException("More than one Author for id " + id);
        } else
            return authorsList.get(0);
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select * from authors", new HashMap<>(), new AuthorMapper());
    }

    @Override
    public Author getAuthorByBookId(long id) {

        final Map<String, Object> params = Collections.singletonMap("bookId", id);

        List<Author> authorsList = jdbc.query("select * from authors inner join books_authors on authors.id=books_authors.author_id" +
                " where books_authors.book_id = :bookId", params, new AuthorMapper());

        if (authorsList.isEmpty()) {
            return null;
        } else {
            return authorsList.get(0);
        }

    }

    @Override
    public Author getByName(String name) throws SQLException {
        final Map<String, Object> params = Collections.singletonMap("authorName", name);

        List<Author> authorsList = jdbc.query("select * from authors where LOWER(name) LIKE LOWER(:authorName)", params, new AuthorMapper());

        if (authorsList.isEmpty()) {
            System.out.println("Could not find author with name " + name);
            return null;
        } else if (authorsList.size() > 1) {
            throw new SQLException("More than one author for name " + name);
        }

        return authorsList.get(0);
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            return new Author(id, name);
        }
    }
}
