package ru.otus.hw27springactuator.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw27springactuator.domain.Author;
import ru.otus.hw27springactuator.domain.Book;
import ru.otus.hw27springactuator.domain.Genre;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksDao extends CrudRepository<Book,Long> {

    List<Book> findByGenres(Genre genre);

    Optional<Book> findByTitle(String title);

    @Query("select b from Book b where b.author = ?1")
    List<Book> findByAuthor(Author author);

    @Query("select b from Book b order by b.id asc")
    List<Book> findAllOrderByIdAsc();

}
