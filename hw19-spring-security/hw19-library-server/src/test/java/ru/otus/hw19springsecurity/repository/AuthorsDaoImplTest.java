package ru.otus.hw19springsecurity.repository;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.hw19springsecurity.domain.Author;
import ru.otus.hw19springsecurity.domain.Book;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ComponentScan("ru.otus.hw19springsecurity.repository")
class AuthorsDaoImplTest {

    private static final String NAME = "Charles Dickens";
    private static final String TITLE = "Oliver Twist";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthorsDao authorsDao;

    @Autowired
    private BooksDao booksDao;


    @Test
    void insert() throws SQLException {
        Author author1 = new Author(NAME);

        this.entityManager.persist(author1);
        this.entityManager.flush();

        Author author2 = this.authorsDao.getByName(NAME).get();

        assertEquals(author1, author2);
    }

    @Test
    void getByName() throws SQLException {
        Author author = new Author(NAME);

        this.entityManager.persist(author);
        this.entityManager.flush();

        Author author2 = this.authorsDao.getByName(NAME).get();

        assertEquals(author, author2);
    }

    @Test
    void findAll() {
        List<Author> authorList = this.authorsDao.findAll();
        assertEquals(5, authorList.size());
    }

    @Test
    void getAuthorByBook() {
        Book book = new Book();
        book.setTitle(TITLE);
        Author author1 = new Author(NAME);
        book.setAuthor(author1);

        this.authorsDao.save(author1);
        this.booksDao.save(book);

        Author author2 = this.authorsDao.getAuthorByBook(book).get();
        assertEquals(author1, author2);
    }
}