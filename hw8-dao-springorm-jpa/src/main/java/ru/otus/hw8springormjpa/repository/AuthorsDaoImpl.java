package ru.otus.hw8springormjpa.repository;


import org.springframework.stereotype.Repository;
import ru.otus.hw8springormjpa.domain.Author;
import ru.otus.hw8springormjpa.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Repository
public class AuthorsDaoImpl implements AuthorsDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long countByName(String name) {
        TypedQuery<Long> query = em.createQuery(
                "select count(a) from Author a where a.name = :name",
                Long.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void insert(Author author) {
        em.persist(author);
    }

    @Override
    public Optional<Author> getByName(String name) throws SQLException {
        Optional<Author> author = Optional.empty();
        try {
            TypedQuery<Author> query = em.createQuery(
                    "select a from Author a where a.name = :name",
                    Author.class);
            query.setParameter("name", name);
            author = Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            author = Optional.empty();
        }

        return author;
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public Optional<Author> getAuthorByBook(Book book) {
        TypedQuery<Author> query = em.createQuery(
                "select b.author from Book b where b = :book",
                Author.class);
        query.setParameter("book", book);
        return Optional.of(query.getSingleResult());
    }
}
