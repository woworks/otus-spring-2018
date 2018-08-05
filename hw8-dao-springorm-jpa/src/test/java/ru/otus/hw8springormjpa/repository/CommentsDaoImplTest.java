package ru.otus.hw8springormjpa.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.hw8springormjpa.domain.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ComponentScan("ru.otus.hw8springormjpa.repository")
class CommentsDaoImplTest {

    private final String BOOK_AUTHOR = "Charles Dickens";
    private final String BOOK_TITLE = "Oliver Twist";
    private final String BOOK_GENRE = "Social novel";
    private final String BOOK_USERNAME = "JohnDoe";
    private final String BOOK_COMMENT = "I like this book!";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CommentsDao commentsDao;

    @Autowired
    private BooksDao booksDao;

    @Test
    void insert() {
        Author author1 = new Author(BOOK_AUTHOR);
        Genre genre1 = new Genre(BOOK_GENRE);

        Book book1 = new Book();
        book1.setTitle(BOOK_TITLE);
        book1.setAuthor(author1);
        book1.setGenres(Arrays.asList(genre1));

        this.entityManager.persist(author1);
        this.entityManager.persist(genre1);
        this.entityManager.persist(book1);
        this.entityManager.flush();

        User user = new User();
        user.setUsername(BOOK_USERNAME);

        Comment comment = new Comment();
        comment.setText(BOOK_COMMENT);
        comment.setUser(user);
        comment.setBook(book1);

        book1.setComments(Arrays.asList(comment));

        this.entityManager.persist(user);
        this.commentsDao.insert(comment);

        this.entityManager.flush();

        Comment comment2 = this.entityManager.find(Comment.class, 1L);
        assertEquals(comment, comment2);
    }

    @Test
    void getCommentsByBook() {
        Author author1 = new Author(BOOK_AUTHOR);
        Genre genre1 = new Genre(BOOK_GENRE);

        Book book1 = new Book();
        book1.setTitle(BOOK_TITLE);
        book1.setAuthor(author1);
        book1.setGenres(Arrays.asList(genre1));

        this.entityManager.persist(author1);
        this.entityManager.persist(genre1);
        this.entityManager.persist(book1);
        this.entityManager.flush();

        User user = new User();
        user.setUsername(BOOK_USERNAME);

        Comment comment = new Comment();
        comment.setText(BOOK_COMMENT);
        comment.setUser(user);
        comment.setBook(book1);

        book1.setComments(Arrays.asList(comment));

        this.entityManager.persist(user);
        this.commentsDao.insert(comment);

        this.entityManager.flush();

        List<Comment> commentList = this.commentsDao.getCommentsByBook(book1);
        assertEquals(1, commentList.size());
        assertEquals(comment, commentList.get(0));
    }
}