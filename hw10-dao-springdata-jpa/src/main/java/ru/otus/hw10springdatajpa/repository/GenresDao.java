package ru.otus.hw10springdatajpa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw10springdatajpa.domain.Book;
import ru.otus.hw10springdatajpa.domain.Genre;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public interface GenresDao extends CrudRepository<Genre, Long> {

    Optional<Genre> getByName(String name) throws SQLException;

    @Query("select b.genres from Book b left join b.genres where b = ?1")
    List<Genre> getGenresByBook(Book book);
}
