package ru.otus.hw6daospringjdbc.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw6daospringjdbc.domain.Author;
import ru.otus.hw6daospringjdbc.domain.Book;
import ru.otus.hw6daospringjdbc.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BooksDaoImpl implements BooksDao {

    private final NamedParameterJdbcOperations jdbc;

    public BooksDaoImpl(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbc = jdbcOperations;
    }

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from books", new HashMap<String, Object>() {}, Integer.class);
    }

    @Override
    //@Transactional
    public void insert(Book book) {


        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource sqlParms =
                new MapSqlParameterSource().addValue("bookTitle", book.getTitle());
        jdbc.update("insert into books (title) values (:bookTitle)", sqlParms,  keyHolder, new String[]{"id"});

        int bookId = keyHolder.getKey().intValue();
        book.setId(bookId);


        for (Genre genre: book.getGenres()){
            Map<String, Object> bookGenresParams = new HashMap<>(2);
            bookGenresParams.put("bookId", book.getId());
            bookGenresParams.put("genreId", genre.getId());
            jdbc.update("insert into books_genres (book_id,genre_id) values (:bookId, :genreId)", bookGenresParams);
        }


        System.out.println("Book to insert = " + book);
        Map<String, Object> bookAuthorsParams = new HashMap<>(2);
        bookAuthorsParams.put("bookId", book.getId());
        bookAuthorsParams.put("authorId", book.getAuthor().getId());
        jdbc.update("insert into books_authors (book_id,author_id) values (:bookId, :authorId)", bookAuthorsParams);

    }

    @Override
    public Book getById(long id) throws SQLException {
        final Map<String, Object> params = Collections.singletonMap("bookId", id);

        List<Book> booksList = jdbc.query("select * from books where id = :bookId", params, new BookMapper());

        if (booksList.isEmpty()) {
            throw new SQLException("Could not find Book with id " + id);
        } else if (booksList.size() > 1) {
            throw new SQLException("More than one Book for id " + id);
        } else
            return booksList.get(0);
    }

    @Override
    public List<Book> getBooksByGenre(Genre genre) {
        final Map<String, Object> params = Collections.singletonMap("genreId", genre.getId());
        return jdbc.query("select * from books inner join books_genres on books.id=books_genres.book_id where books_genres.genre_id = :genreId", params, new BookMapper());
    }

    @Override
    public List<Book> getBooksByAuthor(Author author) {
        final Map<String, Object> params = Collections.singletonMap("authorId", author.getId());
        return jdbc.query("select * from books inner join books_authors on books.id=books_authors.book_id where books_authors.author_id = :authorId", params, new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return  jdbc.query("select * from books", new BookMapper());

    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            return new Book(id, title);
        }
    }
}
