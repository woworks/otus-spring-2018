package ru.otus.hw15springmvcangular.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw15springmvcangular.domain.Author;
import ru.otus.hw15springmvcangular.domain.Book;
import ru.otus.hw15springmvcangular.domain.Genre;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksDao extends CrudRepository<Book,Long> {

    @Query("select b from Book b where ?1 member of b.genres")
    List<Book> findByGenre(Genre genre);

    Optional<Book> findByTitle(String title);

    @Query("select b from Book b where b.author = ?1")
    List<Book> findByAuthor(Author author);

    @Query("select b from Book b order by b.id asc")
    List<Book> findAllOrderByIdAsc();

}
