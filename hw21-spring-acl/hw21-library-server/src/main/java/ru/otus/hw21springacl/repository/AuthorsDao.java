package ru.otus.hw21springacl.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import ru.otus.hw21springacl.domain.Author;
import ru.otus.hw21springacl.domain.Book;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorsDao extends CrudRepository<Author, Long> {

    Optional<Author> getByName(String name) throws SQLException;

    @PostFilter("hasPermission(filterObject, 'READ')")
    List<Author> findAll();

    @Query("select b.author from Book b where b = ?1")
    Optional<Author> getAuthorByBook(Book book);

    @PostAuthorize("hasPermission(returnObject.get(), 'READ')")
    Optional<Author> findById(Long id);

    @PreAuthorize("hasPermission(#author, 'WRITE')")
    Author save(@Param("author")Author author);

}
