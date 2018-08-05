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
import ru.otus.hw8springormjpa.domain.Genre;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ComponentScan("ru.otus.hw8springormjpa.repository")
class BooksDaoImplTest {

    private final String BOOK_AUTHOR = "Charles Dickens";
    private final String BOOK_TITLE = "Oliver Twist";
    private final String BOOK_GENRE = "Social novel";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthorsDao authorsDao;

    @Autowired
    private BooksDao booksDao;

    @Test
    void count() {

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

        long booksCount = this.booksDao.count();

        // 5 added at startup + one new
        assertEquals(6, booksCount);
    }

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

        this.booksDao.insert(book1);
        this.entityManager.flush();

        Book book2 = this.booksDao.getBookByName(BOOK_TITLE).get();

        assertEquals(book1, book2);
    }

    @Test
    void getBooksByGenre() {
        Author author1 = new Author(BOOK_AUTHOR);
        Genre genre1 = new Genre(BOOK_GENRE);

        Book book1 = new Book();
        book1.setTitle(BOOK_TITLE);
        book1.setAuthor(author1);
        book1.setGenres(Arrays.asList(genre1));

        this.entityManager.persist(author1);
        this.entityManager.persist(genre1);

        this.booksDao.insert(book1);
        this.entityManager.flush();

        List<Book> bookList = this.booksDao.getBooksByGenre(genre1);

        assertEquals(1, bookList.size());
        assertEquals(book1, bookList.get(0));
    }

    @Test
    void getBookByName() {
        Author author1 = new Author(BOOK_AUTHOR);
        Genre genre1 = new Genre(BOOK_GENRE);

        Book book1 = new Book();
        book1.setTitle(BOOK_TITLE);
        book1.setAuthor(author1);
        book1.setGenres(Arrays.asList(genre1));

        this.entityManager.persist(author1);
        this.entityManager.persist(genre1);

        this.booksDao.insert(book1);
        this.entityManager.flush();

        Book book2 = this.booksDao.getBookByName(BOOK_TITLE).get();

        assertEquals(book1, book2);
    }

    @Test
    void getBooksByAuthor() {
        Author author1 = new Author(BOOK_AUTHOR);
        Genre genre1 = new Genre(BOOK_GENRE);

        Book book1 = new Book();
        book1.setTitle(BOOK_TITLE);
        book1.setAuthor(author1);
        book1.setGenres(Arrays.asList(genre1));

        this.entityManager.persist(author1);
        this.entityManager.persist(genre1);

        this.booksDao.insert(book1);
        this.entityManager.flush();

        List<Book> bookList = this.booksDao.getBooksByGenre(genre1);

        assertEquals(1, bookList.size());
        assertEquals(book1, bookList.get(0));
    }

    @Test
    void getAll() {
        List<Book> bookList = this.booksDao.getAll();
        assertEquals(5, bookList.size());
    }
}