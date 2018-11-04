package ru.otus.hw21springacl.acl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import ru.otus.hw21springacl.config.acl.ACLContext;
import ru.otus.hw21springacl.config.acl.AclMethodSecurityConfiguration;
import ru.otus.hw21springacl.domain.Author;
import ru.otus.hw21springacl.repository.AuthorsDao;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@EnableAutoConfiguration
@TestExecutionListeners(listeners = {ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        WithSecurityContextTestExecutionListener.class})
@ComponentScan("{ru.otus.hw21springacl.repository, ru.otus.hw21springacl.config.acl}")
@Import({AclMethodSecurityConfiguration.class, ACLContext.class})
public class SpringACLIntegrationTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    AuthorsDao authorsDao;

    @Test
    @WithMockUser(username = "manager")
    public void givenUserManager_whenFindAllAuthors_thenReturnThreeAuthors() {
        List<Author> authors = authorsDao.findAll();
        assertNotNull(authors);
        assertEquals(3, authors.size());
    }

    @Test
    @WithMockUser(username = "manager")
    public void givenUserManager_whenFind1stAuthorByIdAndUpdateItsName_thenOK() {
        Optional<Author> firstAuthorOpt = authorsDao.findById(1L);
        assertTrue(firstAuthorOpt.isPresent());

        Author firstAuthor = firstAuthorOpt.get();
        assertEquals(1L, firstAuthor.getId().longValue());

        firstAuthor.setName("EditedName");
        authorsDao.save(firstAuthor);

        Optional<Author> editedFirstAuthor = authorsDao.findById(1L);

        assertTrue(editedFirstAuthor.isPresent());
        assertEquals(1L, editedFirstAuthor.get().getId().longValue());
        assertEquals("EditedName", editedFirstAuthor.get().getName());
    }


    @Test
    @WithMockUser(username = "user")
    public void givenUsernameUser_whenFindMessageById2_thenOK() {
        Optional<Author> secondAuthor = authorsDao.findById(2L);
        assertTrue(secondAuthor.isPresent());
        assertEquals(2L, secondAuthor.get().getId().longValue());
    }

    @Test
    @WithMockUser(username = "manager")
    public void givenUsernameManager_whenUpdateMessageWithId2_thenFail() {

        Author secondAuthor = new Author();
        secondAuthor.setId(2L);
        secondAuthor.setName("EditedName");
        Executable saveExecutable = () -> authorsDao.save(secondAuthor);
        assertThrows(AccessDeniedException.class, saveExecutable);
    }


    @Test
    @WithMockUser(roles = {"EDITOR"})
    public void givenRoleEditor_whenFindAllMessage_thenReturn3Messages() {
        List<Author> authors = authorsDao.findAll();
        assertNotNull(authors);
        assertEquals(3, authors.size());
    }


    @Test
    @WithMockUser(roles = {"EDITOR"})
    public void givenRoleEditor_whenUpdateThirdAuthor_thenOK() {
        Author thirdAuthor = new Author();
        thirdAuthor.setId(3L);
        thirdAuthor.setName("ThirdAuthor");
        authorsDao.save(thirdAuthor);
    }

    @Test
    @WithMockUser(roles = {"EDITOR"})
    public void givenRoleEditor_whenFind1stAuthorByIdAndUpdateName_thenFail() {
        Optional<Author> firstAuthorOpt = authorsDao.findById(1L);
        assertTrue(firstAuthorOpt.isPresent());
        assertEquals(1, firstAuthorOpt.get().getId().longValue());
        firstAuthorOpt.get().setName("ThirdAuthorEdited");

        Executable saveExecutable = () -> authorsDao.save(firstAuthorOpt.get());
        assertThrows(AccessDeniedException.class, saveExecutable);
    }
}
