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
import ru.otus.hw8springormjpa.domain.Author;
import ru.otus.hw8springormjpa.domain.Book;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ComponentScan("ru.otus.hw8springormjpa.repository")
class AuthorsDaoImplTest {

    private final String AUTHOR_NAME = "Charles Dickens";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthorsDao authorsDao;

    @Autowired
    private BooksDao booksDao;


    @Test
    void countByName() {

        Author author1 = new Author(AUTHOR_NAME);

        this.entityManager.persist(author1);
        this.entityManager.flush();

        long author1Count = this.authorsDao.countByName(AUTHOR_NAME);

        assertEquals(1, author1Count);
    }

    @Test
    void insert() throws SQLException {
        Author author1 = new Author(AUTHOR_NAME);

        this.entityManager.persist(author1);
        this.entityManager.flush();

        Author author2 = this.authorsDao.getByName(AUTHOR_NAME).get();

        assertEquals(author1, author2);
    }

    @Test
    void getByName() throws SQLException {
        Author author = new Author(AUTHOR_NAME);

        this.entityManager.persist(author);
        this.entityManager.flush();

        Author author2 = this.authorsDao.getByName(AUTHOR_NAME).get();

        assertEquals(author, author2);
    }

    @Test
    void getAll() {
        List<Author> authorList = this.authorsDao.getAll();
        assertEquals(5, authorList.size());
    }

    @Test
    void getAuthorByBook() {
        Book book = new Book();
        Author author1 = new Author(AUTHOR_NAME);
        book.setAuthor(author1);

        this.authorsDao.insert(author1);
        this.booksDao.insert(book);

        Author author2 = this.authorsDao.getAuthorByBook(book).get();
        assertEquals(author1, author2);
    }
}