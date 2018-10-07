package ru.otus.hw17webfluxangular.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw17webfluxangular.domain.Book;
import ru.otus.hw17webfluxangular.domain.Genre;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

@Repository
public interface GenresDao extends CrudRepository<Genre, Long> {

    Optional<Genre> getByName(String name) throws SQLException;

    @Query("select b.genres from Book b left join b.genres where b = ?1")
    Set<Genre> getGenresByBook(Book book);
}
