package ru.otus.hw10springdatajpa.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.hw10springdatajpa.domain.*;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ComponentScan("ru.otus.hw10springdatajpa.repository")
class CommentsDaoImplTest {

    private static final String AUTHOR = "Charles Dickens";
    private static final String TITLE = "Oliver Twist";
    private static final String GENRE = "Social novel";
    private static final String USERNAME = "JohnDoe";
    private static final String COMMENT = "I like this book!";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CommentsDao commentsDao;

    @Autowired
    private BooksDao booksDao;

    @Test
    void insert() {
        Author author1 = new Author(AUTHOR);
        Genre genre1 = new Genre(GENRE);

        Book book1 = new Book();
        book1.setTitle(TITLE);
        book1.setAuthor(author1);
        book1.setGenres(Collections.singletonList(genre1));

        this.entityManager.persist(author1);
        this.entityManager.persist(genre1);
        this.entityManager.persist(book1);
        this.entityManager.flush();

        User user = new User();
        user.setUsername(USERNAME);

        Comment comment = new Comment();
        comment.setText(COMMENT);
        comment.setUser(user);
        comment.setBook(book1);

        book1.setComments(Collections.singletonList(comment));

        this.entityManager.persist(user);
        this.commentsDao.save(comment);

        this.entityManager.flush();

        Comment comment2 = this.entityManager.find(Comment.class, 1L);
        assertEquals(comment, comment2);
    }

    @Test
    void getCommentsByBook() {
        Author author1 = new Author(AUTHOR);
        Genre genre1 = new Genre(GENRE);

        Book book1 = new Book();
        book1.setTitle(TITLE);
        book1.setAuthor(author1);
        book1.setGenres(Collections.singletonList(genre1));

        this.entityManager.persist(author1);
        this.entityManager.persist(genre1);
        this.entityManager.persist(book1);
        this.entityManager.flush();

        User user = new User();
        user.setUsername(USERNAME);

        Comment comment = new Comment();
        comment.setText(COMMENT);
        comment.setUser(user);
        comment.setBook(book1);

        book1.setComments(Collections.singletonList(comment));

        this.entityManager.persist(user);
        this.commentsDao.save(comment);

        this.entityManager.flush();

        List<Comment> commentList = this.commentsDao.getCommentsByBook(book1);
        assertEquals(1, commentList.size());
        assertEquals(comment, commentList.get(0));
    }
}