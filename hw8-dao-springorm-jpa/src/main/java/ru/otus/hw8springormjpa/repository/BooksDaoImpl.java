package ru.otus.hw8springormjpa.repository;

import org.springframework.stereotype.Repository;
import ru.otus.hw8springormjpa.domain.Author;
import ru.otus.hw8springormjpa.domain.Book;
import ru.otus.hw8springormjpa.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class BooksDaoImpl implements BooksDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long count() {
        TypedQuery<Long> query = em.createQuery(
                "select count(b) from Book b",
                Long.class);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void insert(Book book) {
        em.persist(book);
    }

    @Override
    public List<Book> getBooksByGenre(Genre genre) {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b where :genre member of b.genres",
                Book.class);
        query.setParameter("genre", genre);
        return query.getResultList();
    }

    @Override
    public Optional<Book> getBookByName(String name) {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b where b.title = :name",
                Book.class);
        query.setParameter("name", name);
        try {
            Book book = query.getSingleResult();
            return Optional.of(book);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Book> getBooksByAuthor(Author author) {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b where b.author = :author",
                Book.class);
        query.setParameter("author", author);
        return query.getResultList();
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b",
                Book.class);
        return query.getResultList();
    }
}
