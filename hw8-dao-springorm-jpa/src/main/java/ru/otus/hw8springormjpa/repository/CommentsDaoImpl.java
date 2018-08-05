package ru.otus.hw8springormjpa.repository;

import org.springframework.stereotype.Repository;
import ru.otus.hw8springormjpa.domain.Book;
import ru.otus.hw8springormjpa.domain.Comment;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CommentsDaoImpl implements CommentsDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public void insert(Comment comment) {
        em.persist(comment);
    }

    @Override
    public List<Comment> getCommentsByBook(Book book) {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b left join fetch b.comments where b = :book",
                Book.class);
        query.setParameter("book", book);
        return query.getSingleResult().getComments();
    }
}
