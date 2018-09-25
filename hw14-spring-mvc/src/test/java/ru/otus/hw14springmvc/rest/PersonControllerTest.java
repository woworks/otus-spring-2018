package ru.otus.hw14springmvc.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.otus.hw14springmvc.domain.Author;
import ru.otus.hw14springmvc.domain.Book;
import ru.otus.hw14springmvc.domain.Genre;
import ru.otus.hw14springmvc.service.AuthorService;
import ru.otus.hw14springmvc.service.BookService;
import ru.otus.hw14springmvc.service.GenreService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PersonController.class)
class PersonControllerTest {

    private static final String AUTHOR = "Charles Dickens";
    private static final String TITLE = "Oliver Twist";
    private static final String NEW_TITLE = "Oliver Twist Updated";
    private static final String GENRE = "Social novel";

    private static List<Book> books = new ArrayList<>();

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @BeforeEach
    public void setUp() {

        books.clear();

        Author author1 = new Author(AUTHOR);
        Genre genre1 = new Genre(GENRE);

        Book book1 = new Book();
        book1.setTitle(TITLE);
        book1.setAuthor(author1);
        book1.setGenres(Collections.singletonList(genre1));

        Book book1updated = new Book();
        book1updated.setTitle(NEW_TITLE);

        Book book2 = new Book();
        book2.setTitle("Cool Book2");
        Book book3 = new Book();
        book3.setTitle("Cool Book3");
        books.add(book1);
        books.add(book2);
        books.add(book3);

        Mockito.when(bookService.findAll())
                .thenReturn(books);

        Mockito.when(bookService.findById(1))
                .thenReturn(Optional.of(book1));

        Mockito.when(bookService.save(anyLong(), anyString(), anyLong(), anyList()))
                .then((Answer<Book>) var1 -> {
                    books.get(0).setTitle(NEW_TITLE);
                    return book1updated;
                });
    }

    @Test
    void listPage() throws Exception {
                boolean hasBookTitle = mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString()
                .contains(TITLE);

                assertTrue(hasBookTitle);
    }

    @Test
    void editPageGet() throws Exception {
        boolean hasBookTitle = mvc.perform(get("/edit").param("id", "1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString()
                .contains(TITLE);

        assertTrue(hasBookTitle);
    }

    @Test
    void editPagePost() throws Exception {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("id", Collections.singletonList("1"));
        params.put("title", Collections.singletonList(NEW_TITLE));
        params.put("author", Collections.singletonList("1"));
        params.put("genres", Collections.singletonList("1"));

        boolean hasBookTitle = mvc.perform(post("/edit").params(params))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString()
                .contains(NEW_TITLE);

        assertTrue(hasBookTitle);
    }
}