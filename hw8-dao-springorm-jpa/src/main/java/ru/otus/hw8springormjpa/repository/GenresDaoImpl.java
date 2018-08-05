package ru.otus.hw8springormjpa.repository;

import org.springframework.stereotype.Repository;
import ru.otus.hw8springormjpa.domain.Book;
import ru.otus.hw8springormjpa.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.*;

@Repository
public class GenresDaoImpl implements GenresDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void insert(Genre genre) {
        em.persist(genre);
    }

    @Override
    public Optional<Genre> getByName(String name) throws SQLException {
        Optional<Genre> genre = Optional.empty();

        try {
            TypedQuery<Genre> query = em.createQuery(
                    "select g from Genre g where g.name = :name",
                    Genre.class);
            query.setParameter("name", name);
            genre = Optional.of(query.getSingleResult());
        } catch (
                NoResultException e) {
            genre = Optional.empty();
        }
        return genre;
    }

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public List<Genre> getGenresByBook(Book book) {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b left join fetch b.genres where b = :book",
                Book.class);
        query.setParameter("book", book);
        return query.getSingleResult().getGenres();
    }
}
