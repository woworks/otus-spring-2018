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
import ru.otus.hw8springormjpa.domain.User;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ComponentScan("ru.otus.hw8springormjpa.repository")
class UsersDaoImplTest {

    private final String BOOK_USERNAME = "JohnDoe";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UsersDao usersDao;

    @Test
    void getUserByUsername() {
        User user1 = new User();
        user1.setUsername(BOOK_USERNAME);

        this.entityManager.persist(user1);
        this.entityManager.flush();

        User user2 = this.usersDao.getUserByUsername(BOOK_USERNAME).get();

        assertEquals(user1, user2);
    }

    @Test
    void insert() {

        User user1 = new User();
        user1.setUsername(BOOK_USERNAME);

        this.usersDao.insert(user1);

        User user2 = this.usersDao.getUserByUsername(BOOK_USERNAME).get();

        assertEquals(user1, user2);
    }
}