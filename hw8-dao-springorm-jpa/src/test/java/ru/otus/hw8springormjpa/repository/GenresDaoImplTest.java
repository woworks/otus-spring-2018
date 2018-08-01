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

import javax.validation.constraints.AssertTrue;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ComponentScan("ru.otus.hw8springormjpa.repository")
class GenresDaoImplTest {

    private final String BOOK_GENRE1 = "Some Cool Genre1";
    private final String BOOK_GENRE2 = "Some Cool Genre2";
    private final String BOOK_AUTHOR = "Charles Dickens";
    private final String BOOK_TITLE = "Oliver Twist";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GenresDao genresDao;

    @Test
    void insert() throws SQLException {
        Genre genre1 = new Genre();
        genre1.setName(BOOK_GENRE1);

        this.genresDao.insert(genre1);

        Genre genre2 = this.genresDao.getByName(BOOK_GENRE1).get();

        assertEquals(genre1, genre2);
    }

    @Test
    void getByName() throws SQLException {
        Genre genre1 = new Genre();
        genre1.setName(BOOK_GENRE1);

        this.entityManager.persist(genre1);

        Genre genre2 = this.genresDao.getByName(BOOK_GENRE1).get();

        assertEquals(genre1, genre2);
    }

    @Test
    void getAll() {

        Genre genre1 = new Genre();
        genre1.setName(BOOK_GENRE1);

        this.entityManager.persist(genre1);

        List<Genre> genreList = this.genresDao.getAll();
        assertNotNull(genreList);
        // 6 from data.sql + 1 just added
        assertEquals(7, genreList.size());
    }

    @Test
    void getGenresByBook() {

        Author author1 = new Author(BOOK_AUTHOR);
        Genre genre1 = new Genre(BOOK_GENRE1);
        Genre genre2 = new Genre(BOOK_GENRE2);

        Book book1 = new Book();
        book1.setTitle(BOOK_TITLE);
        book1.setAuthor(author1);
        book1.setGenres(Arrays.asList(genre1, genre2));

        this.entityManager.persist(author1);
        this.entityManager.persist(genre1);
        this.entityManager.persist(genre2);
        this.entityManager.persist(book1);
        this.entityManager.flush();

        List<Genre> genreList = this.genresDao.getGenresByBook(book1);

        assertTrue(genreList.contains(genre1));
        assertTrue(genreList.contains(genre2));
    }
}