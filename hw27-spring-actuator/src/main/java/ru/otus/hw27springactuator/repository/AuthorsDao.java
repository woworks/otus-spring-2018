package ru.otus.hw27springactuator.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw27springactuator.domain.Author;
import ru.otus.hw27springactuator.domain.Book;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorsDao extends CrudRepository<Author, Long> {

    Optional<Author> getByName(String name) throws SQLException;

    List<Author> findAll();

    @Query("select b.author from Book b where b = ?1")
    Optional<Author> getAuthorByBook(Book book);

}
